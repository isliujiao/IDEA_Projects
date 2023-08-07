package com.example.javatest.graphics2d;

import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.List;

/**
 * 表样属性
 */
@Data
@Accessors(chain = true)
public class Table {
    /**
     * 表头背景色
     */
    private Color headerBackGroundColor;
    /**
     * 表头字体
     */
    private Font headerFont;
    /**
     * 单元格字体
     */
    private Font cellFont;
    /**
     * 表头单元格
     */
    private List<Cell> headCells;
    /**
     * 要合并的列
     */
    private int[] mergeColumns;
    /**
     * 行高
     */
    private int rowHeight = 30;
    /**
     * 上边距
     */
    private int marginY = 10;
    /***
     * 左边距
     */
    private int marginX = 10;
    /**
     *
     */
    private List<List<Cell>> cells;
}
