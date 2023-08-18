package com.example.javatest;

import com.example.javatest.entity.zhongTai.*;
import com.example.javatest.mapper.HelloMapper;
import com.example.javatest.service.HelloService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JavaTestApplicationTests {


    public String imgTime = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());


    public String jsonStr = "{\n" +
            "\t\"bizCode\": \"200\",\n" +
            "\t\"bizDesc\": \"返回成功\",\n" +
            "\t\"orderList\": [{\n" +
            "\t\t\"abilityOrderId\": \"1\",\n" +
            "\t\t\"createTime\": \"2\",\n" +
            "\t\t\"plannedUseDate\": \"3\",\n" +
            "\t\t\"orderOrgName\": \"4\",\n" +
            "\t\t\"orderOrgCode\": \"5\",\n" +
            "\t\t\"orderOrgCoCode\": \"6\",\n" +
            "\t\t\"orderOrgCoName\": \"7\",\n" +
            "\t\t\"appName\": \"8\",\n" +
            "\t\t\"appCode\": \"9\",\n" +
            "\t\t\"deptCode\": \"10\",\n" +
            "\t\t\"deptName\": \"11\",\n" +
            "\t\t\"userCoCode\": \"12\",\n" +
            "\t\t\"userCoName\": \"13\",\n" +
            "\t\t\"contactName\": \"14\",\n" +
            "\t\t\"contactMobile\": \"15\",\n" +
            "\t\t\"contactEmail\": \"16\",\n" +
            "\t\t\"enterpriseCode\": \"17\",\n" +
            "\t\t\"enterpriseName\": \"18\",\n" +
            "\t\t\"departmentName\": \"19\",\n" +
            "\t\t\"subOrderList\": [{\n" +
            "\t\t\t\"subOrderId\": \"20\",\n" +
            "\t\t\t\"suborderStatus\": \"21\",\n" +
            "\t\t\t\"suborderStatusUpdateTime\": \"23\",\n" +
            "\t\t\t\"orderList\": [{\n" +
            "\t\t\t\t\"abilityName\": \"24\",\n" +
            "\t\t\t\t\"abilityCode\": \"25\",\n" +
            "\t\t\t\t\"middleground\": \"9\",\n" +
            "\t\t\t\t\"abitltyVenderName\": \"27\",\n" +
            "\t\t\t\t\"apiZhName\": \"28\",\n" +
            "\t\t\t\t\"apiCode\": \"29\",\n" +
            "\t\t\t\t\"apiBillInfo\": [{\n" +
            "\t\t\t\t\t\"billModeId\": \"30\",\n" +
            "\t\t\t\t\t\"billRullId\": \"31\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"apiStatus\": \"32\",\n" +
            "\t\t\t\t\"apiStatusDesc\": \"33\",\n" +
            "\t\t\t\t\"apiStartTime\": \"34\",\n" +
            "\t\t\t\t\"apiEndTime\": \"35\",\n" +
            "\t\t\t\t\"apiBillStartTime\": \"36\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"abilityName\": \"99\",\n" +
            "\t\t\t\t\"abilityCode\": \"98\",\n" +
            "\t\t\t\t\"middleground\": \"1\",\n" +
            "\t\t\t\t\"abitltyVenderName\": \"97\",\n" +
            "\t\t\t\t\"apiZhName\": \"96\",\n" +
            "\t\t\t\t\"apiCode\": \"99\",\n" +
            "\t\t\t\t\"apiBillInfo\": [{\n" +
            "\t\t\t\t\t\"billModeId\": \"12\",\n" +
            "\t\t\t\t\t\"billRullId\": \"5\"\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"billModeId\": \"4\",\n" +
            "\t\t\t\t\t\"billRullId\": \"12\"\n" +
            "\t\t\t\t}],\n" +
            "\t\t\t\t\"apiStatus\": \"1\",\n" +
            "\t\t\t\t\"apiStatusDesc\": \"2\",\n" +
            "\t\t\t\t\"apiStartTime\": \"33\",\n" +
            "\t\t\t\t\"apiEndTime\": \"2\",\n" +
            "\t\t\t\t\"apiBillStartTime\": \"123124\"\n" +
            "\t\t\t}]\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}";

    @Autowired
    HelloMapper helloMapper;

    @Test
    public void test() {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//        System.out.println("整体JSON------->" + jsonObject + "\n");

        JSONArray orderList = jsonObject.getJSONArray("orderList");//√
//        System.out.println("orderList----> " + orderList + "\n");

        //-------------1、DacpAbility--------------------------------------------
        DacpAbility dacpAbility = (DacpAbility) JSONObject.toBean(jsonObject,DacpAbility.class);
        BeanUtils.copyProperties(jsonObject,dacpAbility);
        dacpAbility.setStataData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String uuidStr = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
        dacpAbility.setAbilityId(uuidStr);
//        dataEXMapper.insterDacpAbility(dacpAbility);
        System.out.println("++++++>> 插入DacpAbility表数据，内容："+ dacpAbility + "\n");
        helloMapper.insterDacpAbility(dacpAbility);

        //-------------2、DacpAbilityRelation--------------------------------------------
        List<DacpAbilityRelation> abilityRelations = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            JSONObject orderJSONObject = orderList.getJSONObject(i);
            DacpAbilityRelation abilityRelation = (DacpAbilityRelation) JSONObject.toBean(orderJSONObject, DacpAbilityRelation.class);
            BeanUtils.copyProperties(orderJSONObject, abilityRelation);
            //设置外键 DacpAbility-abilityId
            abilityRelation.setAbilityId(uuidStr);
            abilityRelations.add(abilityRelation);

            //-------------3、DacpSub--------------------------------------------
            JSONArray subOrderList = orderJSONObject.getJSONArray("subOrderList");
            List<DacpSub> dacpSubs = new ArrayList<>();
            for (int j = 0; j < subOrderList.size(); j++) {
                JSONObject subOrderJSONObject = subOrderList.getJSONObject(j);
                DacpSub dacpSub = (DacpSub) JSONObject.toBean(subOrderJSONObject,DacpSub.class);
                BeanUtils.copyProperties(subOrderJSONObject,dacpSub);
                //设置外键 DacpAbilityRelation-abilityOrderId
                dacpSub.setAbilityOrderId(abilityRelation.getAbilityOrderId());
                dacpSubs.add(dacpSub);

                //-------------4、DacpSubAbility--------------------------------------------
                JSONArray orderList2 = subOrderJSONObject.getJSONArray("orderList");
                List<DacpSubAbility> dacpSubAbilities = new ArrayList<>();
                for (int k = 0; k < orderList2.size(); k++) {
                    JSONObject orderJSONObject2 = orderList2.getJSONObject(k);
                    DacpSubAbility dacpSubAbility = (DacpSubAbility) JSONObject.toBean(orderJSONObject2,DacpSubAbility.class);
                    BeanUtils.copyProperties(orderJSONObject2,dacpSubAbility);
                    //设置外键 DacpSub-subOrderId
                    dacpSubAbility.setSubOrderId(dacpSub.getSubOrderId());
                    dacpSubAbilities.add(dacpSubAbility);

                    //-------------5、DacpSubAbiliytBill--------------------------------------------
                    JSONArray apiBillInfo = orderJSONObject2.getJSONArray("apiBillInfo");
                    List<DacpSubAbiliytBill> dacpSubAbiliytBills = new ArrayList<>();
                    for (int l = 0; l < apiBillInfo.size(); l++) {
                        JSONObject apiBillJSONObject = apiBillInfo.getJSONObject(l);
                        DacpSubAbiliytBill dacpSubAbiliytBill = (DacpSubAbiliytBill) JSONObject.toBean(apiBillJSONObject,DacpSubAbiliytBill.class);
                        BeanUtils.copyProperties(apiBillJSONObject,dacpSubAbiliytBill);
                        //设置外键
                        dacpSubAbiliytBill.setAbilityCode(dacpSubAbility.getAbilityCode());
                        dacpSubAbiliytBills.add(dacpSubAbiliytBill);
                    }
                    //插入 DacpSubAbiliytBill
                    System.out.println("dacpSubAbiliytBills====>" + dacpSubAbiliytBills + "\n");
                    helloMapper.insterDacpSubAbiliytBill(dacpSubAbiliytBills);
                }
                //插入 DacpSubAbility
                System.out.println("dacpSubAbilities====>" + dacpSubAbilities + "\n");
                helloMapper.insterDacpSubAbility(dacpSubAbilities);
            }
            //插入 DacpSub
            System.out.println("dacpSub====>" + dacpSubs + "\n");
            helloMapper.insterDacpSub(dacpSubs);
        }

        //插入 DacpAbilityRelation
        System.out.println("abilityRelations====>" + abilityRelations + "\n");
        helloMapper.insterDacpAbilityRelation(abilityRelations);
    }


}
