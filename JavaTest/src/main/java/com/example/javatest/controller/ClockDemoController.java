package com.example.javatest.controller;

import com.example.javatest.aopdemo.common.RecordLog;
import com.sun.tools.javac.util.Convert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liujiao
 * @date 2023/8/3 14:49
 */
@Controller
public class ClockDemoController {

    private static final Logger logger = LoggerFactory.getLogger(ClockDemoController.class);

    @GetMapping("/clocktest")
    @ResponseBody
    public String test(){
        StopWatch clock = new StopWatch();
        clock.start("从redis读取%d个key耗时:");
        int size = 2000;
        try {Thread.sleep(size);} catch (InterruptedException e) {e.printStackTrace();}
        clock.stop();
        logger.info("{}","c$%^^*&**&$$!@!$$");
        logger.warn(String.format(clock.getLastTaskName(), size) + clock.getTotalTimeSeconds());
        return "clocktest";
    }
}
