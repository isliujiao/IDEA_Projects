package com.example.javatest.entity;

/**
 * @author zagwk
 * @version 1.0
 * @date 2023/7/24 17:37
 */
public enum DepNameEnum {
    AH_location_GIS("安徽位置库GIS能力调用"),
    application_enterprise_trust("企业致信（呼池）"),
    application_index_library("应用部指标库"),
    application_one_iop("一级iop"),
    bank_service("银发服务项目"),
    BG_disaster("位置数据应用系统（逍遥）-应急灾害平台"),
    bigdata_advertisement_service("大数据广告投放服务系统"),
    BJ_government("位置数据应用系统（逍遥）-公安大数据平台"),
    centralization_GD("广东-1+N集中化项目"),
    centralization_HE("集中化一阶段-河北"),
    centralization_HI("海南_集中化"),
    centralization_HL("黑龙江助赢产品项目"),
    centralization_HL_antiFire_tour("集中化一阶段-黑龙江"),
    centralization_JS("江苏-1+N集中化项目"),
    centralization_NM("集中化一阶段-内蒙古"),
    centralization_NX("集中化一阶段-宁夏"),
    centralization_TJ("集中化一阶段-天津"),
    centralization_XJ("集中化一阶段-新疆"),
    centralization_XZ("西藏集中化"),
    cmit_network("物联网及国漫信令监测质量分析"),
    data_management("数据治理平台项目"),
    data_smart_integration("智数集成中心应用"),
    Ecology_cooperation("大数据生态合作平台"),
    Ecology_cooperation_gx("大数据生态合作平台国信项目"),
    external_collect_NM("内蒙公司外部数据采集项目"),
    feixin_laxin("统一互联项目"),
    fourG_Jindowin("5G筋斗云智能管理平台"),
    gansu("集中化一阶段-甘肃"),
    guangdong("广东GIS位置项目"),
    hainan_team("海南租户"),
    henan("河南公司人社就业大数据项目"),
    huayin9("话音九期"),
    hy("杭研-智慧家庭运营项目"),
    hy_health_care("杭研智慧家庭运营项目"),
    integrated_operation_center("报表自助分析系统"),
    Internet_5g_message_security("互联-5G消息安全管控平台"),
    internet_data_operation("互联网公司数据驱动运营项目"),
    IOP_GS("甘肃_集中化"),
    IOP_HE("河北IOP"),
    IOP_HI("海南IOP"),
    IOP_HL("黑龙江IOP"),
    IOP_NM("内蒙古IOP"),
    IOP_TJ("天津_IOP"),
    iot("物联网租户"),
    iot_visitor_violate("物联卡访问人网违规行为项目"),
    jl_team("集中化一阶段-吉林"),
    key_satisfaction_prediction("基于关键因素解耦的满意度预测模型效果提升研究"),
    LN_1_N("辽宁1_N集中化项目"),
    location_study("位置能力中台"),
    market_product_development("市场部产品发展分析项目"),
    net_business_service_analysis("网络事业部服务分析支撑系统"),
    network_data_share("总部网络数据共享平台"),
    nm_wlsjgxpt("网络数据共享平台（内蒙）"),
    ptb_yyfw("运营服务组"),
    qingHai("集中化-青海"),
    qiye_zhixin("企业致信租户"),
    SD_financial_fraud("山东1+N集中化项目"),
    sd_travel_complaint_verification("山东1+N集中化项目"),
    shanghai("“霞客行”区域人群画像"),
    suyan_inner_improve("云数能力内部提升"),
    sy("云能力中心移动云业务日常数据分析"),
    sy_weizhiduanxin("苏研_位置短信项目（全网）"),
    VGOP_GZ_police("集中化对外服务支撑系统"),
    VGOP_location("集中化对外服务支撑系统"),
    VGOP_userPoint("集中化对外服务支撑系统"),
    wt_accurate_touch("梧桐精准触达平台"),
    wutong_dpi("内容洞察项目"),
    wutong_khdc("梧桐大数据_客户洞察租户"),
    wutong_wzdc("梧桐大数据_位置洞察租户"),
    wz_door("位置能力中台"),
    wz_message("位置短信"),
    wz_monitor("位置能力中台"),
    wz_suyan("移动云数据运营系统项目"),
    wz_zhongtai("位置能力中台"),
    xizang("西藏中台运营中心"),
    xizang_yuan("数据治理平台项目"),
    xy("位置数据应用系统（逍遥）"),
    xy_culturaltourism("位置数据应用系统（逍遥）-文旅平台"),
    xy_outside_work("位置数据应用系统（逍遥）-梧桐外勤"),
    xy_population ("位置数据应用系统（逍遥）-人口平台"),
    xy_xhs("逍遥_新华社区域人流感知平台"),
    xz_grid("西藏集中化"),
    xz_iop("西藏IOP"),
    XZ_retail("西藏集中化"),
    yunnan("云南-智云洞察平台"),
    yx_tenant("亚信测试租户"),
    zhengqi_team("政企事业部政企沙盘项目"),
    zj_dsp_advertisement("浙江DSP+RTA项目"),
    zj_financial_anti_fraud("浙江公司金融反诈项目"),
    ZY_marketing_risk_control("全网营销风控项目（全网）"),
    ZY_smart_customer("智慧客户服务管理系统一期工程（原一级客服）");


    private String name;

    DepNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 通过英文代码获取汉字
    public static String getNameByCode(String code) {
        for (DepNameEnum depNameEnum : DepNameEnum.values()) {
            if (depNameEnum.name().equalsIgnoreCase(code)) {
                return depNameEnum.getName();
            }
        }
        return null;
    }
}
