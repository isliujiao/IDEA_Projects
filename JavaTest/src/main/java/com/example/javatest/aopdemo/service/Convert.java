package com.example.javatest.aopdemo.service;

import com.example.javatest.aopdemo.dto.SystemLog;

//入参泛化
public interface Convert<PARAM> {
    //针对不同接口的入参 通过实现Convert接口 来转换成标准日志模型
    SystemLog convert(PARAM param);
}
