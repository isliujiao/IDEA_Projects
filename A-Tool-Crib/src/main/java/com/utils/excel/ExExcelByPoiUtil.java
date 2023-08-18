package com.utils.excel;


import com.utils.date.DateFormatUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 生成Excel的工具类
 */
public final class ExExcelByPoiUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExExcelByPoiUtil.class);
    private static final int TWO = 2;
    private ExExcelByPoiUtil(){

    }
    /**
     * 添加列表信息
     * sheet excelSheet
     * list 导出主要信息
     * fieldName 属性名称>数组对于表头 扩展属性格式extra.key
     * contextStyle 内容样式
     * isHaveSerial 是否添加序号
     */
    public static <T> void addContextByList(HSSFSheet sheet, List<T> list,
                                            String[] fieldName, HSSFCellStyle contextStyle,boolean isHaveSerial) {

        try {
            HSSFRow row = null;
            HSSFCell cell = null;
            if (list != null) {
                List<T> tList = (List<T>) list;
                T t = null;
                String value = "";
                for (int i = 0; i < list.size(); i++) {
                    int rownum = i + TWO;
                    row = sheet.createRow(rownum);
                    for (int j = 0; j < fieldName.length; j++) {

                        t = tList.get(i);
                        value = objectToString(getFieldValueByName(fieldName[j], t));
                        if(isHaveSerial){
                            //首列加序号
                            if(row.getCell(0)!=null && row.getCell(0).getStringCellValue()!=null){
                                String cellVal = ""+i;
                                cell = row.createCell(0);
                                cell.setCellValue(cellVal);
                            }
                            int column = j + 1;
                            cell = row.createCell(column);
                            cell.setCellValue(value);
                        }else{
                            cell = row.createCell(j);
                            cell.setCellValue(value);
                        }
                        cell.setCellStyle(contextStyle);
                    }
                }
                for (int j = 1; j < fieldName.length; j++) {
                    // 单元格宽度 以最大的为准
                    sheet.autoSizeColumn(j);
                }
            } else {
                row = sheet.createRow(TWO);
                cell = row.createCell(0);
            }
        } catch (Exception e) {
            logger.error("填充内容出现错误：", e);
        }
    }


    /**
     * Object转成String类型，便于填充单元格
     * @param object
     * @return
     */
    public static String objectToString(Object object){
        String str = null;
        if(object==null){
            return str;
        }else if(object instanceof Date){
            String parturn = "yyyy-MM-dd HH:mm:ss";
            DateFormat fromType = DateFormatUtil.getSdf(parturn);
            Date date = (Date)object;
            str = fromType.format(date);
        }else if(object instanceof String){
            str = (String)object;
        }else if(object instanceof Integer){
            str = Integer.toString(((Integer)object).intValue());
        }else if(object instanceof Double){
            str = Double.toString(((Double)object).doubleValue());
        }else if(object instanceof Long){
            str = Long.toString(((Long)object).longValue());
        }else if(object instanceof Float){
            str = Float.toHexString(((Float)object).floatValue());
        }else if(object instanceof Boolean){
            str = Boolean.toString((Boolean)object);
        }else if(object instanceof Short){
            str = Short.toString((Short)object);
        }
        return str;
    }

    /**
     * 添加标题(第一行)与表头(第二行)
     *
     * @param
     * sheet excelSheet
     * assettitle 表头>数组
     * titleName 标题
     * headerStyle 标题样式
     * contextStyle  表头样式
     */
    public static void addTitle(HSSFSheet sheet, String[] assettitle, String titleName,
                                HSSFCellStyle headerStyle, HSSFCellStyle contextStyle) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, assettitle.length - 1));
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(titleName);
        cell.setCellStyle(headerStyle);
        row = sheet.createRow(1);
        for (int i = 0; i < assettitle.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(assettitle[i]);
            cell.setCellStyle(contextStyle);
        }
    }

    /**
     *
     * 根据属性名获取属性值
     *
     * fieldName 属性名 object 属性所属对象
     * 支持Map扩展属性, 不支持List类型属性，
     * return 属性值
     */
    @SuppressWarnings("unchecked")
    public static Object getFieldValueByName(String fieldName, Object object) {
        try {
            Object fieldValue = null;
            if (StringUtils.hasLength(fieldName) && object != null) {
                // 首字母
                String firstLetter = "";
                // get方法
                String getter = "";
                // 方法
                Method method = null;
                String extraKey = null;
                // 处理扩展属性 extraData.key
                if (fieldName.indexOf(".") > 0) {
                    String[] extra = fieldName.split("\\.");
                    fieldName = extra[0];
                    extraKey = extra[1];
                }
                firstLetter = fieldName.substring(0, 1).toUpperCase();
                getter = "get" + firstLetter + fieldName.substring(1);
                method = object.getClass().getMethod(getter, new Class[] {});
                fieldValue = method.invoke(object, new Object[] {});
                if (extraKey != null) {
                    Map<String, Object> map = (Map<String, Object>) fieldValue;
                    fieldValue = map==null ? "":map.get(extraKey);
                }
            }
            return fieldValue;
        } catch (Exception e) {
            logger.error("获取属性值出现异常：", e);
            return null;
        }
    }

}
