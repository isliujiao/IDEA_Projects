package org.example.luckydraw.dto;

import lombok.Data;

import java.util.List;

/**
 * 抽奖信息实体类
 *
 * @author liujiao
 */
@Data
public class LotteryConfig {

    /**
     * 抽奖信息id
     */
    private String configId;

    /**
     * 抽奖号码
     */
    private int lotteryNumber;

    /**
     * 添加日期
     */
    private String lotteryDate;

    /**
     * 奖项
     */
    private List<Award> awards;


}
