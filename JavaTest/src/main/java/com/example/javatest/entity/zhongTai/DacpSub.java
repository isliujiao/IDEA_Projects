package com.example.javatest.entity.zhongTai;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/8/10 11:20
 */
@Data
public class DacpSub implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 子订单编码(订单编码2位序号)
     */
    private String subOrderId;

    /**
     * 子订单状态(10：已完成)
     */
    private String suborderStatus;

    /**
     * 更新时间(yyyymmddhhmmss（gmt8北京时间）)
     */
    private String suborderStatusUpdateTime;

    /**
     * DacpAbilityRelation - 能力订购单编码
     */
    private String abilityOrderId;

    /**
     * 订购集合
     */
    private List<DacpSubAbility> orderList;
}
