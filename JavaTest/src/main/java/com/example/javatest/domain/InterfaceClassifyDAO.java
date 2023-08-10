package com.example.javatest.domain;

import lombok.Data;

/**
 * @author liujiao
 * @date 2023/8/7 19:32
 */
@Data
public class InterfaceClassifyDAO {

    /**
     * 当前接口分类
     */
    String classifyName;

    /**
     * 当前接口触发方式  0-用户触发、1-自动产生
     */
    Integer touchOff;
}
