package com.example.javatest.graphics2d;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 单元格属性
 */
@Data
@Accessors(chain = true)
public class Cell {

    /**
     * 行
     */
    private int row;
    /**
     * 列
     */
    private int column;
    /**
     * x 坐标
     */
    private int x;
    /**
     * y 坐标
     */
    private int y;
    /**
     * 当前单元格的父级单元格所在的列
     */
    private int belongColumn;
    /**
     * 单元格宽度,对于有从属单元格的，其宽度由从属单元格决定
     */
    private int width;
    /**
     * 、
     * 合并单元格的情况会用到，默认为行高
     */
    private int height;
    /**
     * 单元格内容
     */
    private String content;
    /**
     * 是否水平居中
     */
    private boolean textAlign;

    /**--------------- 修改 --------------*/
    private int rowspan;
    private int colspan;
    public Cell(String content, int rowspan, int colspan) {
        this.content = content;
        this.rowspan = rowspan;
        this.colspan = colspan;
    }
    /**--------------- 修改 --------------*/

    public Cell(int row, int column, int width, int belongColumn) {
        this.row = row;
        this.column = column;
        this.width = width;
        this.belongColumn = belongColumn;
    }

    public Cell() {
    }

    public Cell(String cellData) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
