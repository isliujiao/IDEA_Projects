package com.utils.graphics2d.schedule;


import com.utils.ftp.FtpClientUtil;
import com.utils.graphics2d.Cell;
import com.utils.graphics2d.DrawTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 保拓攻坚图片生产定制调度
 */
@Component
@EnableScheduling
public class QuartzImgTool {

    /*
     * 日志
     */
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /*
     * 本地文件存储路劲
     */
    @Value("${local.img.path}")
    private String imgPath;
    /**
     * 指定本地字体路径地址
     */
    @Value("${local.fontFile.path}")
    private String fontFilePath;
    /*
     * 远程主机IP
     */
    @Value("${client.ftp.host}")
    private String host;

    /*
     * 远程主机端口
     */
    @Value("${client.ftp.port}")
    private String port;

    /*
     * 远程主机用户名
     */
    @Value("${client.ftp.usernm}")
    private String usernm;

    /*
     * 远程主机用户密码
     */
    @Value("${client.ftp.passwd}")
    private String passwd;

    /*
     * 远程主机默认目录
     */
    @Value("${client.ftp.remoteDir}")
    private String remoteDir;

    /**
     * 定时任务开启关闭控制
     */
    @Value("${scheduled.enabled}")
    private Boolean scheduledEnabled;
    /*
     * 字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /*
     * FTPUtil
     */
    private FtpClientUtil ftpUtil;

    public String time;

    public String imgTime;

    /**
     * 定时删除的周期
     */
    private static final int DAYS_TO_KEEP = 7;

    /**
     * 生成图片定时调度
     * 早9:30-晚20:30，每半点发送一次
     */
    @PostConstruct
    @Scheduled(cron = "0 30 9-21 * * ?")
    public void executeCreateImg(){
        if (scheduledEnabled) {
            ftpUtil = FtpClientUtil.createFtpCli(host,Integer.parseInt(port),usernm,passwd,DEFAULT_CHARSET,remoteDir);
            time = new SimpleDateFormat("yyyyMMdd").format(new Date());
            imgTime = new SimpleDateFormat("yyyyMMddHH").format(new Date());
            LOG.info("------------  生成图片定时调度 start  -----------");
            //判断执行主机

            LOG.info("# 1、生成分公司-日数据");
            boolean flag = createCityDayImg();

            LOG.info("# 2、生成经营网格排名-日数据");
            boolean flagG = createGridRealtimeImg();

            LOG.info("# 3、生成渠道办理排名-日数据");
            boolean flagC = createChannelRealtimeImg();

            LOG.info("------------  生成图片定时调度 end   -----------");
        }
    }

    /**
     * 定时删除(每天凌晨2点执行)
     */
    @PostConstruct
    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteCreateImg(){
        if(scheduledEnabled){
            LOG.info("------------  删除图片定时调度 start   -----------");
            File directory = new File(imgPath);
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                LocalDate thresholdDate = LocalDate.now().minusDays(DAYS_TO_KEEP);
                for (File file : files) {
                    String fileName = file.getName();
                    String[] fileNames = fileName.split("_");
                    if ("XzToBomc".equals(fileNames[0])) {
                        String dateString = fileName.substring(12, 20); // 提取日期部分
                        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                        if (date.isBefore(thresholdDate)) { // 对比当前文件是否大于当前7天前
                            file.delete();
                            System.out.println("文件已删除：" + file.getAbsolutePath());
                        }
                    }
                }
            } else {
                LOG.error("目录不存在：" + imgPath);
            }
            LOG.info("------------  删除图片定时调度 end   -----------");
        }
    }
    /**
     * 生成分公司-日数据
     */
    private boolean createCityDayImg(){
        //获取分公司当日数据
//        List<MigrationData> dataList = getMigrationDataService("day").liveBranch(time);

        //表格数据
        List<List<String>> tableRowContents = new ArrayList<>();
        List<String> value = null;
//        for(MigrationData data : dataList){
//            value = new ArrayList<>();
//            value.add(data.getBranch());
//            value.add(data.getInNum().toString());
//            value.add(data.getOutNum().toString());
//            value.add(data.getFullInNum().toString());
//            value.add(data.getYhlsb().toString());
//            tableRowContents.add(value);
//        }

        //头部信息
        List<Cell> headCells = new ArrayList<>();
        headCells.add(new Cell(1, 1, 110, 1).setTextAlign(true).setContent("分公司"));
        headCells.add(new Cell(1, 2, 110, 1).setTextAlign(true).setContent("迎回用户\n数"));
        headCells.add(new Cell(1, 3, 110, 1).setTextAlign(true).setContent("转网流失\n用户数"));
        headCells.add(new Cell(1, 4, 110, 1).setTextAlign(true).setContent("转网净增\n用户数"));
        headCells.add(new Cell(1, 5, 110, 1).setTextAlign(true).setContent("迎回流失\n比"));

        //文件本地存放路径
        String path = imgPath+"XzToBomc_01_"+imgTime+".png";
        boolean flag = DrawTableUtil.createImgDeal(path,headCells,tableRowContents,
                true,"zhanggui",fontFilePath);

        if(flag){
            //上传文件
            ftpUtil.uploadDir(remoteDir,path);
            LOG.info("# 图片生成完成，路径："+path);
        }else{
            LOG.error("图片生成失败!!");
        }
        return flag;
    }

    /**
     * 城二经营网格排名
     * @return
     */
    private boolean createGridRealtimeImg(){
        //定义查询实体
//        CityQuery cityQuery = new CityQuery();
//        cityQuery.setTime(Integer.valueOf(time));
//        cityQuery.setOrderField("inNum");
//        cityQuery.setOrderWay("2");
//        cityQuery.setDeptCountyId(2); //城二
//        MigrationDataService service = getMigrationDataService("day");
//        //城二经营网格数据
//        List<MigrationGridVo> migrationGridVos = service.queryRealtimeGrid(cityQuery);
//
//        //表格数据
        List<List<String>> tableRowContents = new ArrayList<>();
//        List<String> value = null;
//        for(MigrationGridVo data : migrationGridVos){
//            value = new ArrayList<>();
//            value.add(data.getGridName());
//            value.add(data.getInNum().toString());
//            value.add(data.getOutNum().toString());
//            value.add(data.getFullInNum().toString());
//            tableRowContents.add(value);
//        }

        //头部信息
        List<Cell> headCells = new ArrayList<>();
        headCells.add(new Cell(1, 1, 110, 1).setTextAlign(true).setContent("网格名称"));
        headCells.add(new Cell(1, 2, 110, 1).setTextAlign(true).setContent("迎回用\n户数"));
        headCells.add(new Cell(1, 3, 110, 1).setTextAlign(true).setContent("转网流失\n用户数"));
        headCells.add(new Cell(1, 4, 110, 1).setTextAlign(true).setContent("转网净增\n用户数"));

        //文件本地存放路径
        String path = imgPath+"XzToBomc_02_"+imgTime+".png";
        boolean flag = DrawTableUtil.createImgDeal(path,headCells,tableRowContents,
                true,"zhanggui",fontFilePath);
        if(flag){
            //上传文件
            ftpUtil.uploadDir(remoteDir,path);
            LOG.info("# 图片生成完成，路径："+path);
        }else{
            LOG.error("图片生成失败!!");
        }
        return flag;
    }

    /**
     * 城二经营网格排名
     * @return
     */
    private boolean createChannelRealtimeImg(){
        //定义查询实体
//        CityQuery cityQuery = new CityQuery();
//        cityQuery.setTime(Integer.valueOf(time));
//        cityQuery.setOrderField("inNum");
//        cityQuery.setOrderWay("2");
//        cityQuery.setDeptCountyId(2); //城二
//        MigrationDataService service = getMigrationDataService("day");
//        //城二经营网格数据
//        List<MigrationChannelVo> migrationChannelVos = service.queryRealtimeChannel(cityQuery);
//
//        //表格数据
        List<List<String>> tableRowContents = new ArrayList<>();
//        List<String> value = null;
//        for(MigrationChannelVo data : migrationChannelVos){
//            value = new ArrayList<>();
//            value.add(data.getChannelName());
//            value.add(data.getChannleType());
//            value.add(data.getInNum().toString());
//            tableRowContents.add(value);
//        }

        //头部信息
        List<Cell> headCells = new ArrayList<>();
        headCells.add(new Cell(1, 1, 110, 1).setTextAlign(true).setContent("渠道名称"));
        headCells.add(new Cell(1, 2, 110, 1).setTextAlign(true).setContent("渠道类型"));
        headCells.add(new Cell(1, 3, 110, 1).setTextAlign(true).setContent("迎回\n用户数"));

        //文件本地存放路径
        String path = imgPath+"XzToBomc_03_"+imgTime+".png";
        boolean flag = DrawTableUtil.createImgDeal(path,headCells,tableRowContents,
                true,"zhanggui",fontFilePath);
        if(flag){
            //上传文件
            ftpUtil.uploadDir(remoteDir,path);
            LOG.info("# 图片生成完成，路径："+path);
        }else{
            LOG.error("图片生成失败!!");
        }
        return flag;

    }

//    private MigrationDataService getMigrationDataService(String timeType){
//        return dataServiceMap.get(timeType + "MigrationDataServiceImpl");
//    }
}
