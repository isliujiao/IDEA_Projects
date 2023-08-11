package com.example.javatest.entity.zhongTai;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/8/10 11:19
 */
@Data
public class DacpAbilityRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能力订购单编码(aor年月日4位流水号，aor202104150001)
     */
    private String abilityOrderId;

    /**
     * 创建时间(yyyymmddhhmmss（gmt8北京时间）)
     */
    private String createTime;

    /**
     * 计划使用日期(yyyymmdd)
     */
    private String plannedUseDate;

    /**
     * 能力使用方服务机构名称(能力使用方服务机构名称)
     */
    private String orderOrgName;

    /**
     * 能力使用方服务机构编码(能力使用方服务机构编码)
     */
    private String orderOrgCode;

    /**
     * 能力使用方服务机构所属单位编码(服务机构编码前三位)
     */
    private String orderOrgCoCode;

    /**
     * 能力使用方服务机构所属单位名称(取服务机构编码前三位对应单位名称)
     */
    private String orderOrgCoName;

    /**
     * 应用名称(能力使用方应用名称)
     */
    private String appName;

    /**
     * 应用编码(能力使用方应用编码)
     */
    private String appCode;

    /**
     * 部门编码(oa中内部部门编码)
     */
    private String deptCode;

    /**
     * 部门名称(oa中内部部门名称)
     */
    private String deptName;

    /**
     * 使用方单位编码
     */
    private String userCoCode;

    /**
     * 使用方单位名称
     */
    private String userCoName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机号
     */
    private String contactMobile;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 联系人所属单位编码
     */
    private String enterpriseCode;

    /**
     * 联系人所属单位名称
     */
    private String enterpriseName;

    /**
     * 联系人所属部门
     */
    private String departmentName;

    /**
     * DacpAbility - 能力订购主键
     */
    private String abilityId;
    /**
     * 子订单集合(包含一个或多个子订单)
     */
    private List<DacpSub> subOrderList;

}
