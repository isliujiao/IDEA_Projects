package org.example.luckydraw.service;

import org.example.luckydraw.dto.LotteryConfig;

/**
 * @author liujiao
 * @date 2023/9/4 20:45
 */
public interface LuckyDrawService {

    String simulationPump(LotteryConfig lotteryConfig);
}
