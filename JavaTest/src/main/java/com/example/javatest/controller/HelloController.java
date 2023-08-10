package com.example.javatest.controller;

import com.example.javatest.service.HelloService;
import com.example.javatest.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
