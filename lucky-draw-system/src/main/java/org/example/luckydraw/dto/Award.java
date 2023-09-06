package org.example.luckydraw.dto;

import lombok.Data;

/**
 * 奖项实体类
 *
 * @author liujiao
 */
@Data
public class Award {

    /**
     * 奖项ID
     */
    private Long awardId;
    /**
     * 奖项描述
     */
    private String describe;
    /**
     * 奖项数量
     */
    private int count;
    /**
     * 抽奖信息ID
     */
    private String configId;

}
