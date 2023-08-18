package com.example.javatest;

import com.example.javatest.domain.InterfaceClassify;
import com.example.javatest.domain.InterfaceClassifyDAO;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujiao
 * @date 2023/8/7 19:16
 */
public class InterfaceTest {

    @Test
    public void test(){
        String str = "/nkfxnp/api/inner/dm-cwfk-daping-new/risk_model_treeList";
//        InterfaceClassify interfaceClassify = new InterfaceClassify();
//        System.out.println(interfaceClassify.getInterfaceClassify(str));
        InterfaceClassifyDAO interfaceClassify = getInterfaceClassify(str);
        System.out.println(interfaceClassify.getClassifyName());
        System.out.println(interfaceClassify.getTouchOff());
    }

    /**
     * 风险实时预警
     */
    Map<String, Integer> realTimeWarning = new HashMap<>();
    /**
     * 按业务风险监控
     */
    Map<String, Integer> businessRiskMonitoring = new HashMap<>();
    /**
     * 按单位风险监控
     */
    Map<String, Integer> unitRiskMonitoring = new HashMap<>();
    /**
     * 风险地图
     */
    Map<String, Integer> riskMap = new HashMap<>();

    public InterfaceClassifyDAO getInterfaceClassify(String path) {
        realTimeWarning.put("get_t_1_detailPage", 1);//获取实时风险列表

        businessRiskMonitoring.put("getModelList", 1);//当前风险模型
        businessRiskMonitoring.put("business_riskList", 0);//业务风险监控数据

        unitRiskMonitoring.put("zzdwlbcxList", 1);//专直单位
        unitRiskMonitoring.put("dwfxhzcxList", 1);//单位风险分布汇总查询
        unitRiskMonitoring.put("dwfxflcxList", 1);//预警量
        unitRiskMonitoring.put("dwfxcllzzt", 1);//处理量
        unitRiskMonitoring.put("dwfxjezzt", 1);//金额

        riskMap.put("risk_model_treeList", 1);//风险地图数据

        String[] split = path.split("/");
        String[] strs = split[split.length - 1].split("\\?");
        Map<String, Integer> map = new HashMap<>();
        InterfaceClassifyDAO classifyDAO = new InterfaceClassifyDAO();
        if (realTimeWarning.containsKey(strs[0])) {
            classifyDAO.setClassifyName("风险实时预警");
            classifyDAO.setTouchOff(realTimeWarning.get(strs[0]));
        } else if (businessRiskMonitoring.containsKey(strs[0])) {
            classifyDAO.setClassifyName("按业务风险监控");
            classifyDAO.setTouchOff(businessRiskMonitoring.get(strs[0]));
        } else if (unitRiskMonitoring.containsKey(strs[0])) {
            classifyDAO.setClassifyName("按单位风险监控");
            classifyDAO.setTouchOff(unitRiskMonitoring.get(strs[0]));
        } else if (riskMap.containsKey(strs[0])) {
            classifyDAO.setClassifyName("风险地图");
            classifyDAO.setTouchOff(riskMap.get(strs[0]));
        }
        return classifyDAO;
    }
}
