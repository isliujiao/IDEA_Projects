package com.example.javatest.entity.zhongTai;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/8/10 11:17
 */
@Data
public class DacpAbility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能力订购主键
     */
    private String abilityId;

    /**
     * 数据日期
     */
    private String stataData;

    /**
     * 结果码(1-成功；2-缺少请求参数；3-未查询到数据)
     */
    private String bizCode;

    /**
     * 结果描述
     */
    private String bizDesc;

    /**
     * 能力订购关系集合(当bizcode=1时必传)
     */
    private List<DacpAbilityRelation> orderList;
}
