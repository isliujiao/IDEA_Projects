package com.utils.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 导出07版excel
 */
public class ExcelExportUtil {
    //趋势分析导出
    public Map<String,String> exportExcelqushi(HttpServletRequest request,Map<String, Object> map) throws Exception{
        Map<String,String> resultMap = null;
        try {
            //标题行数据
            List<String> timeList = (List<String>) map.get("timeList");
            timeList.add(0,"地区");
            //表格数据
            Map<String,List<String>> trendMap = (Map<String, List<String>>) map.get("trendMap");

            //组装新数据
            List<List<String>> dataList = new ArrayList<>();
            for (String key : trendMap.keySet()) {
                List<String> chileList = new ArrayList<>();
                chileList.add(key);
                chileList.addAll(trendMap.get(key));
                dataList.add(chileList);
            }
            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表  工作表的名字叫helloWorld
            XSSFSheet sheet = workBook.createSheet("趋势分析数据");
            //标题行
            XSSFRow rowTitle = sheet.createRow(0);
            //设置高度
            rowTitle.setHeightInPoints(30);

            //获取一个样式对象
            CellStyle cellStyle=workBook.createCellStyle();
            XSSFCellStyle styleTemp=((XSSFCellStyle)cellStyle);
            //设置居中对齐
            styleTemp.setAlignment(HorizontalAlignment.CENTER);
            styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);

            //字体加粗变红
            XSSFFont font = workBook.createFont();
            font.setBold(true);
            font.setFontHeight(10);
            styleTemp.setFont(font);

            rowTitle.setRowStyle(styleTemp);

            //标题行
            for (int i = 0; i < timeList.size(); i++) {
                XSSFCell cell = rowTitle.createCell(i);
                cell.setCellValue(timeList.get(i));
                //给标题单元格设置样式
                cell.setCellStyle(cellStyle);
            }
            CellStyle cellStyleRow=workBook.createCellStyle();
            cellStyleRow.setAlignment(HorizontalAlignment.CENTER);
            cellStyleRow.setVerticalAlignment(VerticalAlignment.CENTER);
            //数据行
            for (int i = 0; i < dataList.size(); i++) {

                XSSFRow row = sheet.createRow(i + 1);
                //设置居中对齐
                row.setRowStyle(cellStyleRow);
                List<String> stringList = dataList.get(i);
                for (int j = 0; j < stringList.size(); j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(stringList.get(j));
                }
            }
            String filePath = mkDownloadPath(request) + "kpiqushifenxi" + ".xlsx";
            String fileName = "kpiqushifenxi.xlsx";
            workBook.write(new FileOutputStream(filePath));
            workBook.close();//最后记得关闭工作簿

            resultMap = new HashMap<>();
            //resultMap.put("filePath",mkDownloadPath(request));
            resultMap.put("fileName",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    //排名分析导出
    public Map<String,String> exportExcelrank(HttpServletRequest request,Map<String, Object> map,String kpiName) throws Exception{
        Map<String,String> resultMap = null;
        try {
            //标题行数据
            List<String> timeList = new ArrayList<>();
            timeList.add(0,"地区");
            timeList.add(1,kpiName);
            timeList.add(2,"排名");
            //表格数据
            Map<String,List<String>> trendMap = (Map<String, List<String>>) map.get("trendMap");
            //组装新数据
            List<List<String>> dataList = new ArrayList<>();
            for (String key : trendMap.keySet()) {
                List<String> chileList = new ArrayList<>();
                chileList.addAll(trendMap.get(key));
                chileList.add(key);
                dataList.add(chileList);
            }

            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表  工作表的名字叫helloWorld
            XSSFSheet sheet = workBook.createSheet("排名分析数据");
            //设置列宽
            sheet.setColumnWidth(1, 9000);
            //标题行
            XSSFRow rowTitle = sheet.createRow(0);
            //设置高度
            rowTitle.setHeightInPoints(30);

            //获取一个样式对象
            CellStyle cellStyle=workBook.createCellStyle();
            XSSFCellStyle styleTemp=((XSSFCellStyle)cellStyle);
            //设置居中对齐
            styleTemp.setAlignment(HorizontalAlignment.CENTER);
            styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);

            //字体加粗变红
            XSSFFont font = workBook.createFont();
            font.setBold(true);
            font.setFontHeight(10);
            styleTemp.setFont(font);

            rowTitle.setRowStyle(styleTemp);

            //标题行
            for (int i = 0; i < timeList.size(); i++) {
                XSSFCell cell = rowTitle.createCell(i);
                cell.setCellValue(timeList.get(i));
                //给标题单元格设置样式
                cell.setCellStyle(cellStyle);
            }

            //数据行
            for (int i = 0; i < dataList.size(); i++) {

                XSSFRow row = sheet.createRow(i + 1);
                //设置居中对齐
                List<String> stringList = dataList.get(i);
                for (int j = 0; j < stringList.size(); j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(stringList.get(j));
                    cell.getCellStyle().setWrapText(true);//自动换行
                    cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);//水平居中
                    cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                }
            }
            String filePath = mkDownloadPath(request) + "kpiRank" + ".xlsx";
            String fileName = "kpiRank.xlsx";
            workBook.write(new FileOutputStream(filePath));
            workBook.close();//最后记得关闭工作簿

            resultMap = new HashMap<>();
            //resultMap.put("filePath",mkDownloadPath(request));
            resultMap.put("fileName",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    //排名分析导出
    public Map<String,String> exportExcelComp(HttpServletRequest request,Map<String, Object> map,String kpiName) throws Exception{
        Map<String,String> resultMap = null;
        try {
            //标题行数据
            List<String> timeList = new ArrayList<>();
            timeList.add(0,"地区");
            timeList.add(1,"统计时间");
            timeList.add(2,"对比时间");
            timeList.add(3,"增量");
            timeList.add(4,"增幅");
            //表格数据
            List<LinkedHashMap<String,Object>> trendMap = (List<LinkedHashMap<String, Object>>) map.get("dataListMap");
            List<List<String>> dataList = new ArrayList<>();
            //组装新数据
            for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : trendMap) {
                List<String> chileList = new ArrayList<>();
                String area = (String) stringObjectLinkedHashMap.get("area");
                String time = (String) stringObjectLinkedHashMap.get("time");
                String compareTime = (String) stringObjectLinkedHashMap.get("compareTime");
                String increment = (String) stringObjectLinkedHashMap.get("increment");
                String up = (String) stringObjectLinkedHashMap.get("up");
                chileList.add(0, area);
                chileList.add(1, time);
                chileList.add(2, compareTime);
                chileList.add(3, increment);
                chileList.add(4, up);
                dataList.add(chileList);
            }
            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表  工作表的名字叫helloWorld
            XSSFSheet sheet = workBook.createSheet("对比分析数据");
            //标题行
            XSSFRow rowTitle = sheet.createRow(0);
            //设置高度
            rowTitle.setHeightInPoints(30);

            //获取一个样式对象
            CellStyle cellStyle=workBook.createCellStyle();
            XSSFCellStyle styleTemp=((XSSFCellStyle)cellStyle);
            //设置居中对齐
            styleTemp.setAlignment(HorizontalAlignment.CENTER);
            styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);

            //字体加粗变红
            XSSFFont font = workBook.createFont();
            font.setBold(true);
            font.setFontHeight(10);
            styleTemp.setFont(font);

            rowTitle.setRowStyle(styleTemp);

            //标题行
            for (int i = 0; i < timeList.size(); i++) {
                XSSFCell cell = rowTitle.createCell(i);
                cell.setCellValue(timeList.get(i));
                //给标题单元格设置样式
                cell.setCellStyle(cellStyle);
            }

            //数据行
            for (int i = 0; i < dataList.size(); i++) {

                XSSFRow row = sheet.createRow(i + 1);
                //设置居中对齐
                List<String> stringList = dataList.get(i);
                for (int j = 0; j < stringList.size(); j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(stringList.get(j));
                    cell.getCellStyle().setWrapText(true);//自动换行
                    cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);//水平居中
                    cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                }
            }
            String filePath = mkDownloadPath(request) + "kpiComp" + ".xlsx";
            String fileName = "kpiComp.xlsx";
            workBook.write(new FileOutputStream(filePath));
            workBook.close();//最后记得关闭工作簿

            resultMap = new HashMap<>();
            //resultMap.put("filePath",mkDownloadPath(request));
            resultMap.put("fileName",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    //排名分析导出
    public Map<String,String> exportExcelConst(HttpServletRequest request,Map<String, Object> map,String kpiName) throws Exception{
        Map<String,String> resultMap = null;
        try {
            //标题行数据
            List<String> timeList = new ArrayList<>();
            timeList.add(0,"地区");
            timeList.add(1,"渠道规模");
            timeList.add(2,"占比");
            //表格数据
            List<HashMap<String,Object>> trendMap = (List<HashMap<String, Object>>) map.get("allList1");
            List<HashMap<String,Object>> trendMap2 = (List<HashMap<String, Object>>) map.get("allList2");
            trendMap.addAll(trendMap2);
            List<List<String>> dataList = new ArrayList<>();
            //组装新数据
            for (HashMap<String, Object> hashMap : trendMap) {
                List<String> chileList = new ArrayList<>();
                String area = (String) hashMap.get("area");
                String storeNum = String.valueOf(hashMap.get("storeNum"));
                String account = (String) hashMap.get("account");
                chileList.add(0, area);
                chileList.add(1, storeNum);
                chileList.add(2, account);
                dataList.add(chileList);
            }
            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表  工作表的名字叫helloWorld
            XSSFSheet sheet = workBook.createSheet("构成分析数据");
            //标题行
            XSSFRow rowTitle = sheet.createRow(0);
            //设置高度
            rowTitle.setHeightInPoints(30);

            //获取一个样式对象
            CellStyle cellStyle=workBook.createCellStyle();
            XSSFCellStyle styleTemp=((XSSFCellStyle)cellStyle);
            //设置居中对齐
            styleTemp.setAlignment(HorizontalAlignment.CENTER);
            styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);

            //字体加粗变红
            XSSFFont font = workBook.createFont();
            font.setBold(true);
            font.setFontHeight(10);
            styleTemp.setFont(font);

            rowTitle.setRowStyle(styleTemp);

            //标题行
            for (int i = 0; i < timeList.size(); i++) {
                XSSFCell cell = rowTitle.createCell(i);
                cell.setCellValue(timeList.get(i));
                //给标题单元格设置样式
                cell.setCellStyle(cellStyle);
            }

            //数据行
            for (int i = 0; i < dataList.size(); i++) {

                XSSFRow row = sheet.createRow(i + 1);
                //设置居中对齐
                List<String> stringList = dataList.get(i);
                for (int j = 0; j < stringList.size(); j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(stringList.get(j));
                    cell.getCellStyle().setWrapText(true);//自动换行
                    cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);//水平居中
                    cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
                }
            }
            String filePath = mkDownloadPath(request) + "kpiConst" + ".xlsx";
            String fileName = "kpiConst.xlsx";
            workBook.write(new FileOutputStream(filePath));
            workBook.close();//最后记得关闭工作簿

            resultMap = new HashMap<>();
            //resultMap.put("filePath",mkDownloadPath(request));
            resultMap.put("fileName",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    //创建下载目录
    public String mkDownloadPath(HttpServletRequest request) {
        // 下载模板
//        String rootPath = this.getClass().getResource("/").getPath();
        String rootPath = request.getSession().getServletContext().getRealPath(File.separator);

        if (!rootPath.endsWith(File.separator)) {
            rootPath = rootPath + File.separator;
        }
        String downloadPath = rootPath + "downloadFile" + File.separator;
        File file = new File(downloadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return downloadPath;
    }

    //全量指标数据导出
    public Map<String,String> exportExcelFullKpi(HttpServletRequest request,List<Map<String,String>>FullDoseKpi) throws Exception{
        //标题行数据
        List<String> kpiHeadList = new ArrayList<String>();;
        kpiHeadList.add(0,"指标编码");
        kpiHeadList.add(1, "指标名称");
        kpiHeadList.add(2, "指标SQL");
        kpiHeadList.add(3, "业务口径");
        kpiHeadList.add(4, "归属应用报表名");
        kpiHeadList.add(5, "底层数据字段名");
        kpiHeadList.add(6, "单位");
        kpiHeadList.add(7, "指标用途");
        kpiHeadList.add(8, "指标粒度");
        kpiHeadList.add(9, "需求部门");
        kpiHeadList.add(10, "需求类型");
        kpiHeadList.add(11, "业务口径");
        kpiHeadList.add(12, "应用系统展示名");
        kpiHeadList.add(13, "所属分析主题");
        kpiHeadList.add(14, "指标下沉粒度");
        kpiHeadList.add(15, "开发方");
        kpiHeadList.add(16, "开发人");
        kpiHeadList.add(17, "大数据负责人");
        kpiHeadList.add(18, "技术口径");
        kpiHeadList.add(19, "指标类型");
        kpiHeadList.add(20, "SQL脚本");
        kpiHeadList.add(21, "指标所在底层表名");
        kpiHeadList.add(22, "指标分类-I");
        kpiHeadList.add(23, "指标分类-II");
        kpiHeadList.add(24, "指标分类-III");
        kpiHeadList.add(25, "指标开放度");
        kpiHeadList.add(26, "指标及时性");
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        XSSFSheet sheet = workBook.createSheet("全量指标");
        //标题行
        XSSFRow rowTitle = sheet.createRow(0);
        //设置高度
        rowTitle.setHeightInPoints(30);
        //获取一个样式对象
        CellStyle cellStyle = workBook.createCellStyle();
        XSSFCellStyle styleTemp = ((XSSFCellStyle) cellStyle);
        //设置居中对齐
        styleTemp.setAlignment(HorizontalAlignment.CENTER);
        styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);
        //字体加粗变红
        XSSFFont font = workBook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        styleTemp.setFont(font);
        rowTitle.setRowStyle(styleTemp);
        //标题行
        for (int i = 0; i < kpiHeadList.size(); i++) {
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(kpiHeadList.get(i));
            //给标题单元格设置样式
            cell.setCellStyle(cellStyle);
        }
        CellStyle cellStyleRow = workBook.createCellStyle();
        cellStyleRow.setAlignment(HorizontalAlignment.CENTER);
        cellStyleRow.setVerticalAlignment(VerticalAlignment.CENTER);
        //数据行
        for (int i = 0; i < FullDoseKpi.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            //设置居中对齐
            row.setRowStyle(cellStyleRow);
            Map<String, String> objectMap = FullDoseKpi.get(i);
            for (int j = 1; j <= 27; j++) {
                XSSFCell cell = row.createCell(j-1);
                if (j == 1) {
                    cell.setCellValue(objectMap.get("kpi_code"));
                }else if (j == 2) {
                    cell.setCellValue(objectMap.get("kpi_name"));
                }else if (j == 3) {
                    cell.setCellValue(objectMap.get("kpi_sql"));
                }else if (j == 4) {
                    cell.setCellValue(objectMap.get("kpi_des"));
                }else if (j == 5) {
                    cell.setCellValue(objectMap.get("report_name"));
                }else if (j == 6) {
                    cell.setCellValue(objectMap.get("field_name"));
                }else if (j == 7) {
                    cell.setCellValue(objectMap.get("show_unit"));
                }else if (j == 8) {
                    cell.setCellValue(objectMap.get("kpi_purpose"));
                }else if (j == 9) {
                    cell.setCellValue(objectMap.get("kpi_granularity"));
                }else if (j == 10) {
                    cell.setCellValue(objectMap.get("demand_department"));
                }else if (j == 11) {
                    cell.setCellValue(objectMap.get("statistical_type"));
                }else if (j == 12) {
                    cell.setCellValue(objectMap.get("business_caliber"));
                }else if (j == 13) {
                    cell.setCellValue(objectMap.get("system_display_name"));
                }else if (j == 14) {
                    cell.setCellValue(objectMap.get("analysis_subject"));
                }else if (j == 15) {
                    cell.setCellValue(objectMap.get("kpi_subsidence_granularity"));
                }else if (j == 16) {
                    cell.setCellValue(objectMap.get("develop_company"));
                }else if (j == 17) {
                    cell.setCellValue(objectMap.get("developer"));
                }else if (j == 18) {
                    cell.setCellValue(objectMap.get("big_data_director"));
                }else if (j == 19) {
                    cell.setCellValue(objectMap.get("technical_caliber"));
                }else if (j == 20) {
                    cell.setCellValue(objectMap.get("kpi_type_new"));
                }else if (j == 21) {
                    cell.setCellValue(objectMap.get("sql_script"));
                }else if (j == 22) {
                    cell.setCellValue(objectMap.get("sql_name"));
                }else if (j == 23) {
                    cell.setCellValue(objectMap.get("kpi_type_one"));
                }else if (j == 24) {
                    cell.setCellValue(objectMap.get("kpi_type_two"));
                }else if (j == 25) {
                    cell.setCellValue(objectMap.get("kpi_type_three"));
                }else if (j == 26) {
                    String indicator_openness = objectMap.get("indicator_openness");
                    if (Objects.equals(indicator_openness, "0")) {
                        indicator_openness="31省指标";
                    }else if (Objects.equals(indicator_openness, "1")) {
                        indicator_openness="本省指标";
                    }else if (Objects.equals(indicator_openness, "2")) {
                        indicator_openness="敏感指标";
                    }
                    cell.setCellValue(indicator_openness);
                }else if(j == 27) {
                	String indicator_timeliness = objectMap.get("indicator_timeliness");
                	cell.setCellValue(indicator_timeliness);
                }
            }
        }
        Map<String, String> resultMap = null;
        String filePath = mkDownloadPath(request) + "fullDoseKpi" + ".xlsx";
        String fileName = "fullDoseKpi.xlsx";
        workBook.write(new FileOutputStream(filePath));
        workBook.close();//最后记得关闭工作簿
        resultMap = new HashMap<>();
        //resultMap.put("filePath", mkDownloadPath(request));
        resultMap.put("fileName", fileName);
        return resultMap;
    }

    //已申请指标导出
    public Map<String,String> exportExcelkpi (HttpServletRequest request,List<Map<String,String>>alreadyKpi) throws Exception{
        Map<String, String> resultMap = null;
        //标题行数据
        List<String> kpiHeadList = new ArrayList<String>();;
        kpiHeadList.add(0,"序号");
        kpiHeadList.add(1, "指标编码");
        kpiHeadList.add(2, "指标名称");
        kpiHeadList.add(3, "指标单位");
        kpiHeadList.add(4, "需求部门");
        kpiHeadList.add(5, "所属申请单");
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        XSSFSheet sheet = workBook.createSheet("已申请指标");
        //标题行
        XSSFRow rowTitle = sheet.createRow(0);
        //设置高度
        rowTitle.setHeightInPoints(30);
        //获取一个样式对象
        CellStyle cellStyle = workBook.createCellStyle();
        XSSFCellStyle styleTemp = ((XSSFCellStyle) cellStyle);
        //设置单元格列宽
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 15000);
        //设置居中对齐
        styleTemp.setAlignment(HorizontalAlignment.CENTER);
        styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);
        //字体加粗变红
        XSSFFont font = workBook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        styleTemp.setFont(font);
        rowTitle.setRowStyle(styleTemp);
        //标题行
        for (int i = 0; i < kpiHeadList.size(); i++) {
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(kpiHeadList.get(i));
            //给标题单元格设置样式
            cell.setCellStyle(cellStyle);
        }
        CellStyle cellStyleRow = workBook.createCellStyle();
        cellStyleRow.setAlignment(HorizontalAlignment.CENTER);
        cellStyleRow.setVerticalAlignment(VerticalAlignment.CENTER);
        //数据行
        for (int i = 0; i < alreadyKpi.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            //设置居中对齐
            row.setRowStyle(cellStyleRow);
            Map<String, String> objectMap = alreadyKpi.get(i);
            for (int j = 0; j < objectMap.size(); j++) {
                XSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(i+1);
                }if (j == 1) {
                    cell.setCellValue(objectMap.get("kpi_code"));
                }if (j == 2) {
                    cell.setCellValue(objectMap.get("kpi_name"));
                }if (j == 3) {
                    cell.setCellValue(objectMap.get("kpi_unit"));
                }if (j == 4) {
                    cell.setCellValue(objectMap.get("requ_dept"));
                }if (j == 5) {
                    cell.setCellValue(objectMap.get("applyList_name"));
                }
            }
        }
        String filePath = mkDownloadPath(request) + "kpiData" + ".xlsx";
        String fileName = "kpiData.xlsx";
        workBook.write(new FileOutputStream(filePath));
        workBook.close();//最后记得关闭工作簿
        resultMap = new HashMap<>();
        //resultMap.put("filePath", mkDownloadPath(request));
        resultMap.put("fileName", fileName);
        return resultMap;

    }



    /**
     * @Author: hly
     * @DateTime: 2021/11/29 10:47
     * @Description: 下载上传指标模板
     */
    public Map<String,String> exportExcelkpiTemplate (HttpServletRequest request) throws Exception{
        Map<String, String> resultMap = null;
        //标题行数据
        List<String> kpiHeadList = new ArrayList<String>();;
        kpiHeadList.add(0,"指标编码（必填）");
        kpiHeadList.add(1, "指标名称");
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        XSSFSheet sheet = workBook.createSheet("新建指标上传模板");
        //标题行
        XSSFRow rowTitle = sheet.createRow(0);
        //设置高度
        rowTitle.setHeightInPoints(30);
        //获取一个样式对象
        CellStyle cellStyle = workBook.createCellStyle();
        XSSFCellStyle styleTemp = ((XSSFCellStyle) cellStyle);
        //设置单元格列宽
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 10000);
        //设置居中对齐
        styleTemp.setAlignment(HorizontalAlignment.CENTER);
        styleTemp.setVerticalAlignment(VerticalAlignment.CENTER);
        //字体加粗变红
        XSSFFont font = workBook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        styleTemp.setFont(font);
        rowTitle.setRowStyle(styleTemp);
        //标题行
        for (int i = 0; i < kpiHeadList.size(); i++) {
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellValue(kpiHeadList.get(i));
            //给标题单元格设置样式
            cell.setCellStyle(cellStyle);
        }
        String filePath = mkDownloadPath(request) + "kpiDataTemplate" + ".xlsx";
        String fileName = "kpiDataTemplate.xlsx";
        workBook.write(new FileOutputStream(filePath));
        workBook.close();//最后记得关闭工作簿
        resultMap = new HashMap<>();
        //resultMap.put("filePath", mkDownloadPath(request));
        resultMap.put("fileName", fileName);

        return resultMap;
    }

}

