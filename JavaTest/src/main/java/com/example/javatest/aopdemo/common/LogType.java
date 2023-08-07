package com.example.javatest.aopdemo.common;

/**
 * @author liujiao
 * @date 2023/8/4 16:38
 */
public enum LogType {
    /**
     *
     */
    SELECT("查询"),
    INSERT("插入"),
    UPDATE("更新"),
    DELETE("删除");

    private String value;

    private LogType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
