package org.example.luckydraw.service.impl;

import org.example.luckydraw.dto.Award;
import org.example.luckydraw.dto.LotteryConfig;
import org.example.luckydraw.dto.Winner;
import org.example.luckydraw.dto.WinnerList;
import org.example.luckydraw.mapper.AwardMapper;
import org.example.luckydraw.mapper.LotteryConfigMapper;
import org.example.luckydraw.mapper.WinnerMapper;
import org.example.luckydraw.service.LuckyDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiao
 * @date 2023/9/4 20:45
 */
@Service
public class LuckyDrawServiceImpl implements LuckyDrawService {

    @Autowired
    private LotteryConfigMapper lotteryConfigMapper;

    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private WinnerMapper winnerMapper;


    @Override
    public String simulationPump(LotteryConfig lotteryConfig) {

        //1、初始化奖池总配置
        String primaryKey = UUID.randomUUID().toString().replace('-', ' ').substring(0, 8);
        lotteryConfig.setConfigId(primaryKey);
        lotteryConfig.setLotteryDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        lotteryConfigMapper.insertLotteryConfig(lotteryConfig);

        //2、初始化当前奖池奖项明细及数量
        List<Award> awards = lotteryConfig.getAwards();
        for (Award award : awards) {
            award.setConfigId(primaryKey);
        }
        awardMapper.insertAward(awards);

        //3、开始模拟抽奖
        try {
            pumpAwards(lotteryConfig);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4、展示所有获奖人和所得奖项
        List<WinnerList> winnerList = winnerMapper.selectWinnerList(lotteryConfig.getConfigId());
        return winnerList.toString();
    }

    /**
     * 异步抽奖 - 模拟1000人进行抽奖
     *
     * @param lotteryConfig 奖项配置
     * @throws InterruptedException 异常
     */
    private void pumpAwards(LotteryConfig lotteryConfig) throws InterruptedException {
        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    latch.countDown();
                    latch.await();
                    String lottery;
                    synchronized (this) {
                        //获奖人名
                        String randomName = UUID.randomUUID().toString().replace('-', ' ').substring(0, 8);
                        lottery = lottery(randomName, lotteryConfig);
                    }
                    System.out.println(lottery);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 关闭线程池
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    /**
     * 进行抽奖
     *
     * @param name
     * @return
     */
    public String lottery(String name, LotteryConfig lotteryConfig) {
        if (lotteryConfig == null) {
            return "抽奖配置不存在";
        }
        // 获取抽奖配置的剩余奖品数量
        int remainingLotteryNumber = lotteryConfig.getLotteryNumber();
        synchronized (this) {
            if (remainingLotteryNumber <= 0) {
                return "奖品已经抽完";
            }
            List<Award> awards = awardMapper.selectAwards(lotteryConfig.getConfigId());
            if (null == awards) {
                return "award奖项不存在";
            }

            //查看是否还有奖品
            long awardId = 0;
            for (Award award : awards) {
                if (award.getCount() > 0) {
                    awardId = award.getAwardId();
                    break;
                }
            }
            if (0 != awardId) {
                int count = awardMapper.selectCount(awardId);
                if (count > 0) {
                    // 生成获奖人员
                    Winner winner = new Winner();
                    winner.setName(name);
                    winner.setAwardId(awardId);
                    winner.setConfigId(lotteryConfig.getConfigId());
                    winnerMapper.insertWinner(winner);
                    //更新当前奖项信息
                    awardMapper.updateAwardCount(awardId, count - 1);
                    // 更新抽奖配置的剩余奖品数量
                    remainingLotteryNumber--;
                }
            }

        }
        // 更新抽奖配置的剩余奖品数量
        lotteryConfig.setLotteryNumber(remainingLotteryNumber);
        lotteryConfigMapper.updateLotteryNumber(lotteryConfig);
        return "成功抽取";
    }

}
