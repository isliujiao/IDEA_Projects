package com.example.javatest.controller;


import com.example.javatest.domain.HelloDO;
import com.example.javatest.graphics2d.Cell;
import com.example.javatest.graphics2d.DrawTableUtil;
import com.example.javatest.graphics2d.Table;
import com.example.javatest.mapper.HelloMapper;
import com.example.javatest.service.HelloService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.javatest.graphics2d.DrawTableUtil.drawTable;

/**
 * @author liujiao
 * @date 2023/7/14 18:31
 */
@Controller
@Slf4j
public class Graphics2dController {

    @Autowired
    private HelloService helloService;

    @Autowired
    HelloMapper helloMapper;

    public String time = new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * 本地文件存储路劲
     */
    @Value("${local.img.path}")
    private String imgPath;

    @GetMapping(value = "/dataImage")
    @ResponseBody
    public String list() throws IOException {
        Boolean result = executeCreateImg();
        return String.valueOf(result);
    }

    public Boolean executeCreateImg() throws IOException {
        List<Cell> headCells = new ArrayList<>();
        // Cell控制表头的行row、列column
        headCells.add(new Cell(1, 1, 100, 0).setTextAlign(true).setContent("指标名称"));
        headCells.add(new Cell(1, 2, 100, 0).setTextAlign(true).setContent("通服收入"));
        headCells.add(new Cell(1, 3, 100, 0).setTextAlign(true).setContent("CHN收入"));
        headCells.add(new Cell(1, 4, 100, 0).setTextAlign(true).setContent("携入携出比"));

        headCells.add(new Cell(2, 2, 100, 2).setTextAlign(true).setContent("年累计值"));
        headCells.add(new Cell(2, 3, 100, 2).setTextAlign(true).setContent("年累计目标值"));
        headCells.add(new Cell(2, 4, 100, 2).setTextAlign(true).setContent("完成率"));
        headCells.add(new Cell(2, 5, 100, 2).setTextAlign(true).setContent("预警状态"));

        headCells.add(new Cell(2, 6, 100, 3).setTextAlign(true).setContent("年累计值"));
        headCells.add(new Cell(2, 7, 100, 3).setTextAlign(true).setContent("年累计目标值"));
        headCells.add(new Cell(2, 8, 100, 3).setTextAlign(true).setContent("完成率"));
        headCells.add(new Cell(2, 9, 100, 3).setTextAlign(true).setContent("预警状态"));

        headCells.add(new Cell(2, 10, 100, 4).setTextAlign(true).setContent("年累计值"));
        headCells.add(new Cell(2, 11, 100, 4).setTextAlign(true).setContent("年累计目标值"));
        headCells.add(new Cell(2, 12, 100, 4).setTextAlign(true).setContent("完成率"));
        headCells.add(new Cell(2, 13, 100, 4).setTextAlign(true).setContent("预警状态"));

        List<List<String>> tableRowContents = new ArrayList<>();
        //查询数据
        List<HelloDO> kpiExportDataDTOS = helloMapper.selectList();
        List<String> value = null;
        for (HelloDO dataDTO : kpiExportDataDTOS) {
            value = new ArrayList<>();
            value.add(dataDTO.getCountyName());
            value.add(dataDTO.getTfYearAccu());
            value.add(dataDTO.getTfTarget());
            value.add(dataDTO.getTfPerComplete());
            value.add(dataDTO.getTfIsWarn());

            value.add(dataDTO.getChnYearAccu());
            value.add(dataDTO.getChnTarget());
            value.add(dataDTO.getChnPerComplete());
            value.add(dataDTO.getChnIsWarn());

            value.add(dataDTO.getXrxcYearAccu());
            value.add(dataDTO.getChnTarget());
            value.add(dataDTO.getXrxcPerComplete());
            value.add(dataDTO.getXrxcIsWarn());
            tableRowContents.add(value);
        }
        //文件本地存放路径
        String path = imgPath + "kpi数据---------" + time + ".png";
        BufferedImage imgDeal1 = createImgDeal(path, headCells, tableRowContents);
        List<BufferedImage> images = new ArrayList<>();
        // 读取图片文件
        File file1 = new File("F://kpi//a.png");
        File file2 = new File("F://kpi//b.png");
        BufferedImage image1 = ImageIO.read(file1);
        BufferedImage image2 = ImageIO.read(file2);
        images.add(image1);
        images.add(image2);
//        images.add(imgDeal1);
//        images.add(imgDeal1);
        mergeImages(images, imgPath + "is测试导出图片.png");
        return true;
    }
    @SneakyThrows
    public static BufferedImage createImgDeal(String path, List<Cell> headCells, List<List<String>> tableRowContents) {
        boolean flag = false;
        BufferedImage image = null;
        try {
            int[] mergeColumns = new int[]{};
            Table table = new Table().setCellFont(new Font("宋体", Font.PLAIN, 10))
                    .setHeadCells(headCells).setHeaderFont(new Font("宋体", Font.BOLD, 15))
                    .setHeaderBackGroundColor(new Color(203,238,254))
                    .setMergeColumns(mergeColumns);
            image = drawTable(table, tableRowContents);
            ImageIO.write(image, "png", new File(path));
        } catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public static boolean mergeImages(List<BufferedImage> images, String outputPath) {
        boolean flag = false;
        try {
            int maxWidth = 0;
            int totalHeight = 0;

            // calculate the maximum width and total height of the images
            for (BufferedImage image : images) {
                maxWidth = Math.max(maxWidth, image.getWidth());
                totalHeight += image.getHeight();
            }

            // create a new image with the desired dimensions
            BufferedImage mergedImage = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);

            // draw each image in the list onto the new image
            Graphics g = mergedImage.getGraphics();
            int y = 0;
            for (BufferedImage image : images) {
                g.drawImage(image, 0, y, null);
                y += image.getHeight();
            }

            // save the merged image to the output path
            ImageIO.write(mergedImage, "png", new File(outputPath));

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }}
