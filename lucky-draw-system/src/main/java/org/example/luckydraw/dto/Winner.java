package org.example.luckydraw.dto;

import lombok.Data;

/**
 * 获奖人员实体类
 *
 * @author liujiao
 */
@Data
public class Winner {

    /**
     * 获奖人ID
     */
    private Long winId;
    /**
     * 获奖人名字
     */
    private String name;
    /**
     * 奖项ID
     */
    private Long awardId;
    /**
     * 抽奖信息
     */
    private String configId;

}
