package org.example.luckydraw.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.luckydraw.dto.LotteryConfig;

/**
 * @author liujiao
 * @date 2023/9/4 21:04
 */
@Mapper
public interface LotteryConfigMapper {
    void insertLotteryConfig(LotteryConfig lotteryConfig);

    LotteryConfig selectLotteryConfig();

    int updateLotteryNumber(LotteryConfig lotteryConfig);

}
