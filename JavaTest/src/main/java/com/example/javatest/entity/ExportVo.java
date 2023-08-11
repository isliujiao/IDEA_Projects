package com.example.javatest.entity;

import lombok.Data;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/7/6 15:50
 */
@Data
public class ExportVo {
    //开始时间（yyyyMMddHHmm）
    private String startDt;
    //结束时间（yyyyMMddHHmm）
    private String endDt;
    //重传次数（00/01/02......）
    private String reTime;
}
