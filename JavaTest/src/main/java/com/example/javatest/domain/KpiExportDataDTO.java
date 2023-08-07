package com.example.javatest.domain;

import lombok.Data;

/**
 * @author liujiao
 * @date 2023/7/18 11:22
 */
@Data
public class KpiExportDataDTO {
    /**
     * 指标名称
     */
    private String countyName;
    /**
     * 年累计
     */
    private String YearAccu;
    /**
     * 年累计目标值
     */
    private String Target;
    /**
     * 完成率
     */
    private String PerComplete;
    /**
     * 预警状态
     */
    private String tfIsWarn;

}
