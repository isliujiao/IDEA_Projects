package com.example.javatest.mapper;


import com.example.javatest.domain.HelloDO;
import com.example.javatest.entity.zhongTai.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liujiao
 * @date 2023/7/17 9:54
 */
@Mapper
public interface HelloMapper {

    List<HelloDO> selectList();

    String getMap();

    void insterDacpAbility(DacpAbility dacpAbility);

    void insterDacpAbilityRelation(List<DacpAbilityRelation> abilityRelations);

    void insterDacpSub(List<DacpSub> dacpSubs);

    void insterDacpSubAbility(List<DacpSubAbility> dacpSubAbilities);

    void insterDacpSubAbiliytBill(List<DacpSubAbiliytBill> dacpSubAbiliytBills);

    void insterStr(@Param("com.isliujiao.gpt.chatgpt.test") String test);
}
