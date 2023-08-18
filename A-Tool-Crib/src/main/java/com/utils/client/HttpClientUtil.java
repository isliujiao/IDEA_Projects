package com.utils.client;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * HTTP 客户端请求工具类
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 发送https请求
     *
     * @param requestUrl 请求地址
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject postHttpsRequest(String requestUrl, String outputStr) {
        logger.debug("================= 发送https请求 POST START ===================");
        logger.debug("#请求地址："+requestUrl);
        logger.debug("#提交数据："+outputStr);
        JSONObject jsonObject = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(requestUrl);
            JSONObject json = JSONObject.fromObject(outputStr);
            System.out.println("入参报文 ： "+json.toString());
            StringEntity s = new StringEntity(json.toString(), "UTF-8");
            s.setContentType("application/json;charset=GBK");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            System.out.println(res.getStatusLine().getStatusCode());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result=EntityUtils.toString(entity,"UTF-8");
                logger.debug("#返回值："+result);
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("================= 发送https请求 POST END ===================");
        return jsonObject;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl 请求地址
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject getHttpsRequest(String requestUrl) {
        logger.debug("================= 发送https请求 GET START ===================");
        logger.debug("#请求地址："+requestUrl);
        JSONObject jsonObject = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(requestUrl);
            HttpResponse res = client.execute(get);
            System.out.println(res.getStatusLine().getStatusCode());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result=EntityUtils.toString(entity,"UTF-8");
                logger.debug("#返回值："+result);
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("================= 发送https请求 GET END ===================");
        return jsonObject;
    }





    /*
     * https请求
     * requestUrl：请求地址
     * requestMethod:GET / POST
     * outputStr:POST请求数据,GET请传NULL
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
