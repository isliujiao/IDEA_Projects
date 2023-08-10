package com.example.javatest.domain;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liujiao
 * @date 2023/8/7 18:11
 */
@Data
public class InterfaceClassify {

    /**
     * 风险实时预警
     */
    Map<String, Integer> realTimeWarning;
    /**
     * 按业务风险监控
     */
    Map<String, Integer> businessRiskMonitoring;
    /**
     * 按单位风险监控
     */
    Map<String, Integer> unitRiskMonitoring;
    /**
     * 风险地图
     */
    Map<String, Integer> riskMap;

    public Map<String, Integer> getInterfaceClassify(String path) {
        realTimeWarning.put("get_t_1_detailPage", 1);//获取实时风险列表

        businessRiskMonitoring.put("getModelList", 1);//当前风险模型
        businessRiskMonitoring.put("business_riskList", 2);//业务风险监控数据

        unitRiskMonitoring.put("zzdwlbcxList", 1);//专直单位
        unitRiskMonitoring.put("dwfxhzcxList", 1);//单位风险分布汇总查询
        unitRiskMonitoring.put("dwfxflcxList", 1);//预警量
        unitRiskMonitoring.put("dwfxcllzzt", 1);//处理量
        unitRiskMonitoring.put("dwfxjezzt", 1);//金额

        riskMap.put("risk_model_treeList", 1);//风险地图数据

        String[] split = path.split("/");
        String[] strs = split[split.length - 1].split("\\?");
        Map<String, Integer> map = new HashMap<>();
        if (realTimeWarning.containsKey(strs[0])) {
            map.put("风险实时预警", realTimeWarning.get(strs[0]));
        } else if (businessRiskMonitoring.containsKey(strs[0])) {
            map.put("按业务风险监控", realTimeWarning.get(strs[0]));
        } else if (unitRiskMonitoring.containsKey(strs[0])) {
            map.put("按单位风险监控", realTimeWarning.get(strs[0]));
        } else {
            map.put("风险地图", riskMap.get(strs[0]));
        }
        return map;
    }
}
