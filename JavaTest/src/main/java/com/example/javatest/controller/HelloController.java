package com.example.javatest.controller;

import com.example.javatest.service.HelloService;
import com.example.javatest.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author liujiao
 * @date 2023/7/20 16:27
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/getmap")
    @ResponseBody
    public String getMap(){
        return helloService.getMap();
    }

    @GetMapping("/insterTest")
    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    public String insterTest(){
        String map = helloService.getMap();
        helloService.inster("测试" + UUID.randomUUID().toString().substring(0,5));
        return map;
    }
}
