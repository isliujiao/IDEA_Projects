package org.example.luckydraw.dto;

import lombok.Data;

/**
 * 获奖名单
 *
 * @author liujiao
 */
@Data
public class WinnerList {

    /**
     * 获奖人员姓名
     */
    private String name;

    /**
     * 所得奖项
     */
    private String awardDescribe;

}
