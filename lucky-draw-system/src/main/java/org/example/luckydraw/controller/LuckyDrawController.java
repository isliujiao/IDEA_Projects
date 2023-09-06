package org.example.luckydraw.controller;

import org.example.luckydraw.dto.LotteryConfig;
import org.example.luckydraw.service.LuckyDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liujiao
 * @date 2023/9/4 20:41
 */
@RestController
@RequestMapping("/luckyDraw")
public class LuckyDrawController {

    @Autowired
    public LuckyDrawService luckyDrawService;

    /**
     * 进行抽奖
     *
     * @return
     */
    @PostMapping("/lottery")
    @ResponseBody
    public String lottery(@RequestBody LotteryConfig lotteryConfig) {
        return luckyDrawService.simulationPump(lotteryConfig);
    }

}
