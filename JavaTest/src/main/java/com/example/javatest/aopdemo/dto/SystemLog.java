package com.example.javatest.aopdemo.dto;

import java.util.Date;

/**
 * @author liujiao
 * @date 2023/8/4 14:56
 */
public class SystemLog {
    private String id;
    /**
     * 访问时间
     */
    private Date createTime;
    /**
     * 操作人
     */
    private String username;
    /**
     * 访问ip
     */
    private String ip;
    /**
     * 访问资源url
     */
    private String url;
    /**
     * 执行时间
     */
    private Long executionTime;
    /**
     * 访问方法
     */
    private String method;
}

