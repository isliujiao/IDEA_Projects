package com.example.javatest.aopdemo.controller;

import com.example.javatest.aopdemo.common.LogType;
import com.example.javatest.aopdemo.common.RecordLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liujiao
 * @date 2023/8/4 14:58
 */
@Controller
public class AopDemoController {

    private final Logger logger = LoggerFactory.getLogger(AopDemoController.class);

    @GetMapping("/aopDemo")
    @ResponseBody
    @RecordLog(desc = "AOP 测试",logType = LogType.SELECT)
    public String aopDemo(String str,Integer index) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "<h1>AOP - Demo</h1>";
    }
}
