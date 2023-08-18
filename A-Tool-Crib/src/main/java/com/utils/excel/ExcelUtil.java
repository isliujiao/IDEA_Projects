package com.utils.excel;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * excel处理工具类
 */
public final class ExcelUtil {

    private final static Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);
    private ExcelUtil(){

    }
    /**
     * 获取Excel工作对象
     * @param file
     * @return
     */
    public static Workbook getWorkBook(File file) throws Exception {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (Exception e) {
            LOG.error("exception",e);
        }
        return workbook;
    }

    /**
     * 获取Excel的sheet
     * @param work
     * @param sheetIndex
     * @return
     */
    public static Sheet getSheet(Workbook work, int sheetIndex){
        Sheet sheet = null;
        if(work.getNumberOfSheets() > 0){
            sheet = work.getSheetAt(sheetIndex);
        }
        return sheet;
    }

    /**
     * 获取sheet页中的行
     * @param sheet
     * @param rowIndex
     * @return
     */
    public static Row getRow(Sheet sheet, int rowIndex){
        return sheet.getRow(rowIndex);
    }

    /**
     * 获取单元格数据
     * @param row
     * @param cellIndex
     * @return
     */
    public static Cell getCell(Row row, int cellIndex){
        return row.getCell(cellIndex);
    }


}
