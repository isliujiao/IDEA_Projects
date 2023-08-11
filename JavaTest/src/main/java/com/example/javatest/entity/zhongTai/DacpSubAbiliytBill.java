package com.example.javatest.entity.zhongTai;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/8/10 11:21
 */
@Data
public class DacpSubAbiliytBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 计费模型id
     */
    private String billModeId;

    /**
     * 计费规则id(billmodetype=5-多规则阶梯计费模型时必填)
     */
    private String billRullId;

    /**
     * DacpSubAbility - 能力编码
     */
    private String abilityCode;

}
