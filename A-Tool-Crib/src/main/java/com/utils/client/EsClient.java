package com.utils.client;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class EsClient {
    private static Logger LOG = LoggerFactory.getLogger(EsClient.class);
    public final static String EsTabPrefix ="dacp_dataex_cdr";
//    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    //ES服务端设置
    @Value("${com.asiainfo.dacp.dataex.application.log.esServer}")
    private String esServerVa;
    @Value("${com.asiainfo.dacp.dataex.application.log.esIsAuth}")
    private String isVerifyVa;
    @Value("${com.asiainfo.dacp.dataex.application.log.esUserName}")
    private String userNameVa;
    @Value("${com.asiainfo.dacp.dataex.application.log.esPassword}")
    private String passwordVa;

    private static String esServer;
    private static String isVerify;
    private static String userName;
    private static String password;

    @PostConstruct
    public void getEsServer(){ esServer = this.esServerVa; }
    @PostConstruct
    public void getIsVerify(){ isVerify = this.isVerifyVa; }
    @PostConstruct
    public void getUserName(){ userName = this.userNameVa; }
    @PostConstruct
    public void getPassword(){ password = this.passwordVa; }


    //ES客户端端连接池设置
    private static int maxConnectPerRoute =10;//最大路由连接数，每一个 local IP => remote IP:port 为一个route
    private static int maxConnectNum =maxConnectPerRoute*3; //连接池最大并发数，乘以es节点node数量
    private static int connectTimeout =6000;//连接超时时间
    private static int socketTimeout =30000;//Socket连接超时时间
    private static int connectionRequestTimeout =60000;//获取连接的超时时间

    private static RestHighLevelClient client;
    public static RestHighLevelClient getRestHighLevelClient(){
        if(client ==null){
            init();
        }
        return client;
    }
    private static void init(){
        if(StringUtils.isBlank(isVerify)){
            isVerify ="false";
        }
        if(StringUtils.isNotBlank(esServer)){
            LOG.info("RestHighLevelClient init");
            String[] servers = esServer.split(",");
            List<HttpHost> list=new ArrayList<HttpHost>();
            for(String serverStr :servers){
                String[] server =serverStr.split(":");
                HttpHost httpHost = new HttpHost(server[0], Integer.valueOf(server[1]), "http");
                list.add(httpHost);
            }
            if(list.size() >0){
                maxConnectNum = maxConnectPerRoute *list.size();
                HttpHost[] httpHosts=new HttpHost[list.size()];
                HttpHost[] hosts = list.toArray(httpHosts);
                RestClientBuilder builder;
                if("true".equalsIgnoreCase(isVerify)){
                    //需要认证
                    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    // 设置账号密码
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
                    builder = RestClient.builder(hosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback(){
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            httpClientBuilder.setMaxConnTotal(maxConnectNum);
                            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                            return httpClientBuilder;
                        }
                    });
                }else{
                    builder = RestClient.builder(hosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback(){
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            httpClientBuilder.setMaxConnTotal(maxConnectNum);
                            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                            return httpClientBuilder;
                        }
                    });
                }
                builder.setRequestConfigCallback(requestConfigBuilder -> {
                    requestConfigBuilder.setConnectTimeout(connectTimeout);
                    requestConfigBuilder.setSocketTimeout(socketTimeout);
                    requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
                    return requestConfigBuilder;
                });
                client = new RestHighLevelClient(builder);
            }
        }
        if(client ==null) LOG.error("es无法连接，请检查es客户端配置文件是否正确！！！！！");
    }

    /**
     * 依据查询时间段生成有效的索引名组合
     * @param offset     结束时间偏移量,冗余延迟入库情况
     * @param startDate  查询开始日期
     * @param endDate    查询结束日期
     * @return
     */
    public static String[] getIndices(int offset, String startDate,String endDate)
            throws IOException {
        if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) return null;
        //依据查询时间段生成对应索引名
        String prefix = EsClient.EsTabPrefix;
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
//        try {
//            start.setTime(sdf.parse(startDate));
//            end.setTime(sdf.parse(endDate));
//        }catch(Exception e) {
//            LOG.error("getIndices SimpleDateFormat caused error:{}" ,e.getMessage());
//            return null;
//        }
        start.setTimeInMillis(Long.parseLong(startDate));
        end.setTimeInMillis(Long.parseLong(endDate));
        if(start.compareTo(end)<=0){
            end.add(Calendar.DATE, offset<0?0:offset);
            ArrayList<String> indices=new ArrayList<String>();
            while(start.compareTo(end)<=0 ) {
                indices.add(prefix + "-" + sdf.format(start.getTime()));
                start.add(Calendar.DATE,1);
            }
            //筛选出有效的索引
            ArrayList<String> activeList=new ArrayList<String>();
            for(int i=0; i<indices.size(); i++) {
                GetIndexRequest request = new GetIndexRequest();
                request.indices(indices.get(i));
                if(getRestHighLevelClient().indices().exists(request,RequestOptions.DEFAULT)) {
                    activeList.add(indices.get(i));
                }
            }
            String[] array = new String[activeList.size()];
            activeList.toArray(array);
            LOG.info("getIndices return:{}" , Arrays.toString(array));
            return array;
        }
        return null;
    }

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        //builder.addHeader("Authorization", "Bearer " + TOKEN);
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

}
