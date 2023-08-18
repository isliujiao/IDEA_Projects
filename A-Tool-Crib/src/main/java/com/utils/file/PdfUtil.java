package com.utils.file;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * PDF工具类
 */
@Slf4j
public class PdfUtil {

    /**
     * 根据模板导出单个PDF文件(支持多页模板)
     * @param templateName 模板文件路径全称
     * @param fileName 目标文件名称
     * @param response 响应请求
     * @param data 数据Map
     */
    public void exportPdf(String templateName, String fileName, HttpServletResponse response, Map<String, Object> data){
        try{
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        // 设置响应头
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;fileName=" + fileName + ".pdf");
        OutputStream out = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper = null;
        PdfReader reader = null;
        //PDF模板页数
        int pageNum = 10;
        try {
            // 输出到浏览器端
            out = response.getOutputStream();
            // 字节数组流，用来缓存文件流
            ByteArrayOutputStream[] baos = new ByteArrayOutputStream[pageNum];
            // 给表单生成中文字体
            BaseFont fonChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 遍历模板，给pdf表单赋值
            for (int i =0;i<pageNum;i++){
                baos[i] = new ByteArrayOutputStream();
                InputStream ins = this.getClass().getResourceAsStream(templateName);
                reader = new PdfReader(ins);
                stamper = new PdfStamper(reader,baos[i]);
                AcroFields form = stamper.getAcroFields();
                form.addSubstitutionFont(fonChinese);
                for(String key : data.keySet()){
                    if (data.get(key) != null){
                        form.setField(key, data.getOrDefault(key," ").toString());
                    }
                }
                // 表明该PDF不可修改
                stamper.setFormFlattening(true);
                stamper.close();
            }
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            for (int i=0;i<pageNum;i++){
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(baos[i].toByteArray()), i+1);
                copy.addPage(importPage);
            }
            doc.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据模板导出多个PDF并打包成ZIP
     * @param templateName 模板文件路径全称
     * @param fileName 目标文件名
     * @param response 响应
     * @param listData 数据实体集合
     */
    public void exportZip(String templateName, String fileName, HttpServletResponse response, List<Map<String, Object>> listData) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 设置响应头
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".zip");
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            if (!listData.isEmpty()) {
                for (Map<String, Object> data : listData) {
                    if (data.get("fileName") != null) {
                        fileName = data.get("fileName").toString() + ".pdf";
                    }
                    byte[] xmpMetadata = generatePdfStream(templateName, data).toByteArray();
                    //将PDF文件添加到压缩卷中
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                    zipOutputStream.write(xmpMetadata);
                    zipOutputStream.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(zipOutputStream != null){
                    zipOutputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据模板生成 PDF流
     * @param templateName 模板文件名
     * @param data 数据实体
     * @return 字节流
     * @throws Exception
     */
    private static ByteArrayOutputStream generatePdfStream(String templateName, Map<String, Object> data) throws  Exception{
        InputStream ins = new ClassPathResource(templateName).getInputStream();
        PdfReader reader = new PdfReader(ins);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);
        PdfContentByte under = ps.getUnderContent(1);
        //使用中文字体
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<>();
        fontList.add(bf);
        //取出报表模板中的所有字段
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        for (String key : data.keySet()) {
            if (data.get(key) != null) {
                fields.setField(key, data.get(key).toString());
            }
        }
        ps.setFormFlattening(true);
        ps.close();
        return bos;
    }


    /**
     *
     * @param value 文本
     * @param font 字体
     * @param horizontalAlignment 水平样式 0-left, 1-center, 2-right
     * @param verticalAlignment 垂直样式  4-top, 5-middle, 6-bottom;
     * @param colspan 列合并
     * @param rowspan 行合并
     * @param borderSide 外边框
     *  0-默认
     *  1-隐藏上边框
     *  2-隐藏下边框
     *  3-隐藏上、下边框
     *  4-隐藏左边框
     *  5-隐藏左、上边框
     *  6-隐藏左、下边框
     *  7-隐藏左、上、下边框
     *  8-隐藏右边框
     *  9-隐藏右、上边框
     *  10-隐藏右、下边框
     *  11-隐藏右、上、下边框
     *  12-隐藏左、右边框
     *  13-隐藏上、左、右边框
     *  14-隐藏下、左、右边框
     *  15-隐藏全部
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int horizontalAlignment, int verticalAlignment, int colspan, int rowspan, int borderSide) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(value, font));
        //水平居中
        cell.setHorizontalAlignment(horizontalAlignment);
        if(verticalAlignment>0){
            //垂直居中
            cell.setUseAscender(true);
        }
        //垂直居中
        cell.setVerticalAlignment(verticalAlignment);
        if(colspan>0 ){
            cell.setColspan(colspan);
        }
        if(rowspan>0){
            cell.setRowspan(rowspan);
        }
        if(borderSide>0){
            cell.disableBorderSide(borderSide);
        }
        return cell;
    }

    /**
     * 无模板导出PDF
     * @param fileName 导出文件名
     * @param response  返回响应
     * @param data 要打包的数据
     */
    public static void generatePDFDoc(String fileName, HttpServletResponse response, Map<String, Object> data){
        try{
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        // 设置响应头
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;fileName=" + fileName + ".pdf");
        OutputStream out = null;
        Document doc = new Document(PageSize.A4);
        try {
            out = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();
            doc = makeSinglePDF(doc,data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭文档
            if(doc!=null){
                doc.close();
            }
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出Zip压缩文件
     * @param fileName 导出文件名
     * @param response 输出响应
     * @param listData 数据
     */
    public static void generatePDFZip(String fileName, HttpServletResponse response, List<Map<String, Object>> listData) {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 设置响应头
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".zip");
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            if (!listData.isEmpty()) {
                for (Map<String, Object> data : listData) {
                    if (data.get("fileName") != null) {
                        fileName = data.get("fileName").toString() + ".pdf";
                    }
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                    Document document = new Document();
                    PdfWriter writer = PdfWriter.getInstance(document, zipOutputStream);
                    writer.setCloseStream(false);
                    document.open();
                    document = makeSinglePDF(document,data);
                    document.close();
                    zipOutputStream.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(zipOutputStream != null){
                    zipOutputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成单个PDF文档
     * @param data 数据键值对，根据实际情况修改键名称
     * @return
     */
    private static Document makeSinglePDF(Document doc,Map<String, Object> data){
        try {
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bf, 12, Font.NORMAL, BaseColor.BLACK);
            Font fontTile = new Font(bf, 14, Font.BOLD, BaseColor.BLACK);
            Paragraph paragraph1 = new Paragraph("日常值守记录表", fontTile);
            paragraph1.setAlignment(PdfPCell.ALIGN_CENTER);
            //设置行间距
            paragraph1.setLeading(15f);
            //设置段落下空白
            paragraph1.setSpacingAfter(20f);

            PdfPTable tbText = new PdfPTable(new float[] { 1f, 1.5f, 1f, 0.8f,1.6f, 3f});
            tbText.addCell(createCell("日期：", font, PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_LEFT, 0,0,15));
            tbText.addCell(createCell(data.getOrDefault("coamDutyDate"," ") == null ? " " :
                            data.getOrDefault("coamDutyDate"," ").toString(), font,
                    PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_MIDDLE, 0,0,15));
            tbText.addCell(createCell("班次：", font, PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_LEFT, 0,0,15));
            tbText.addCell(createCell(data.getOrDefault("coamClassName"," ") == null ? " " :
                            data.getOrDefault("coamClassName"," ").toString(), font,
                    PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_MIDDLE, 0,0,15));
            tbText.addCell(createCell("值守人员：", font, PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_LEFT, 0,0,15));
            tbText.addCell(createCell(data.getOrDefault("coamPesonName"," ") == null ? " " :
                            data.getOrDefault("coamPesonName"," ").toString(), font,
                    PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_LEFT, 0,0,15));
            //增加空行
            tbText.addCell(createCell(" ",font,PdfPCell.ALIGN_LEFT, PdfPCell.ALIGN_LEFT,6,2,15));
            PdfPTable table = new PdfPTable(new float[] { 1f, 1.5f, 1f, 1.5f});
            //表头
            table.addCell(createCell("开始时间", font, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE, 0,0,0));
            table.addCell(createCell("结束时间", font, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            table.addCell(createCell("运行状态", font, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            table.addCell(createCell("备注", font, PdfPCell.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            //动态表格
            table.addCell(createCell(data.getOrDefault("coamClassessStarttime"," ") == null ? " ":
                            data.getOrDefault("coamClassessStarttime"," ").toString(), font,
                    Element.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            table.addCell(createCell(data.getOrDefault("coamClassessEndtime"," ") == null ? " ":
                            data.getOrDefault("coamClassessEndtime"," ").toString(), font,
                    Element.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            table.addCell(createCell(data.getOrDefault("coamRunStatus"," ") == null ? " ":
                            data.getOrDefault("coamRunStatus"," ").toString(), font,
                    Element.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            table.addCell(createCell(data.getOrDefault("coamLogRemark"," ") == null ? " " :
                            data.getOrDefault("coamLogRemark"," ").toString(), font,
                    Element.ALIGN_CENTER, PdfPCell.ALIGN_MIDDLE,0,0,0));
            doc.add(paragraph1);
            doc.add(tbText);
            doc.add(table);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }
}
