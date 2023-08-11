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
public class DacpSubAbility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能力编码
     */
    private String abilityCode;

    /**
     * 能力名称
     */
    private String abilityName;

    /**
     * 能力所属中台(1：业务中台，2：数据中台，9：技术中台)
     */
    private String middleground;

    /**
     * 能力提供方名称
     */
    private String abitltyVenderName;

    /**
     * 服务名称
     */
    private String apiZhName;

    /**
     * 服务编码
     */
    private String apiCode;

    /**
     * 服务开通结果(01：开通成功，02：开通失败，03：退订成功)
     */
    private String apiStatus;

    /**
     * 服务开通结果描述(服务开通失败时填写失败原因)
     */
    private String apiStatusDesc;

    /**
     * 服务开通开始时间(yyyymmddhhmmss（gmt8北京时间）当apistatus=01时必填)
     */
    private String apiStartTime;

    /**
     * 服务开通终止时间(yyyymmddhhmmss（gmt8北京时间）当apistatus=01时如果为空则为无限期)
     */
    private String apiEndTime;

    /**
     * 服务计费开始时间(yyyymmddhhmmss（gmt8北京时间）)
     */
    private String apiBillStartTime;

    /**
     * DacpSub - 子订单编码(订单编码2位序号)
     */
    private String subOrderId;

    /**
     * 服务计费信息	服务需要结算时必填
     */
    private List<DacpSubAbiliytBill> apiBillInfo;
}
