package com.example.javatest.service.impl;

import com.example.javatest.domain.HelloDO;
import com.example.javatest.mapper.HelloMapper;
import com.example.javatest.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujiao
 * @date 2023/7/17 9:52
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloMapper helloMapper;

    @Override
    public List<HelloDO> select() {
        List<HelloDO> helloDOS = helloMapper.selectList();
        return helloDOS;
    }

    @Override
    public String getMap() {

        return helloMapper.getMap();
    }
}
