package org.example.luckydraw;

import org.example.luckydraw.mapper.LotteryConfigMapper;
import org.example.luckydraw.service.LuckyDrawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckyDrawSystemApplicationTests {

    @Autowired
    private LuckyDrawService luckyDrawService;

    @Autowired
    private LotteryConfigMapper lotteryConfigMapper;

    @Test
    public void testLotteryConcurrent() throws InterruptedException {
        int threadCount = 1000;
        CountDownLatch latch = new CountDownLatch(threadCount);

        initLuckData();


        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int number = i;
            executorService.execute(() -> {
                try {
                    latch.countDown();
                    latch.await();
                    String lottery;
//                    synchronized (luckyDrawService) {
//                        String randomName = UUID.randomUUID().toString().replace('-', ' ').substring(0, 8);
//                        lottery = luckyDrawService.lottery(randomName);
//                    }
//                    System.out.println(lottery);
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
     * 创建奖项信息
     */
    private void initLuckData() {

    }
}
