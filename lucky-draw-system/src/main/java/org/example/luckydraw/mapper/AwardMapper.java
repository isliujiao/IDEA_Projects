package org.example.luckydraw.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.luckydraw.dto.Award;

import java.util.List;

/**
 * @author liujiao
 * @date 2023/9/4 21:04
 */
@Mapper
public interface AwardMapper {

    void insertAward(List<Award> award);

    int updateAwardCount(@Param("awardId") Long awardId, @Param("count") Integer count);

    List<Award> selectAwards(@Param("configId") String configId);

    int selectCount(@Param("awardId") Long awardId);
}
