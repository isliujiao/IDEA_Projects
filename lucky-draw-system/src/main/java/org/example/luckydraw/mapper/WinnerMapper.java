package org.example.luckydraw.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.luckydraw.dto.Winner;
import org.example.luckydraw.dto.WinnerList;

import java.util.List;

/**
 * @author liujiao
 * @date 2023/9/4 21:04
 */
@Mapper
public interface WinnerMapper {

    void insertWinner(Winner winner);

    int countByAward(@Param("configId") String configId);

    List<WinnerList> selectWinnerList(@Param("id") String id);
}
