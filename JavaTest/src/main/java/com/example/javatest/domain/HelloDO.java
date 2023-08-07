package com.example.javatest.domain;

import lombok.Data;

/**
 * @author liujiao
 * @date 2023/7/17 16:34
 */
@Data
public class HelloDO {
    /**
     * 指标名称
     */
    private String countyName;
    /**
     * 年累计（通服收入）
     */
    private String tfYearAccu;
    /**
     * 年累计目标值（通服收入）
     */
    private String tfTarget;
    /**
     * 完成率（通服收入）
     */
    private String tfPerComplete;
    /**
     * 预警状态（通服收入）
     */
    private String tfIsWarn;

    /**
     * 年累计（CHN收入）
     */
    private String chnYearAccu;
    /**
     * 年累计目标值（CHN收入）
     */
    private String chnTarget;
    /**
     * 完成率（CHN收入）
     */
    private String chnPerComplete;
    /**
     * 预警状态（CHN收入）
     */
    private String chnIsWarn;

    /**
     * 年累计（携入携出比）
     */
    private String xrxcYearAccu;
    /**
     * 年累计目标值（携入携出比）
     */
    private String xrxcTarget;
    /**
     * 完成率（携入携出比）
     */
    private String xrxcPerComplete;
    /**
     * 预警状态（携入携出比）
     */
    private String xrxcIsWarn;
}
