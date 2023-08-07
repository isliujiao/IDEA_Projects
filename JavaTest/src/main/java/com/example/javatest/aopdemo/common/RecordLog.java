package com.example.javatest.aopdemo.common;


import com.example.javatest.aopdemo.service.Convert;

import java.lang.annotation.*;

/**
 * @author isliujiao
 */
@Target(ElementType.METHOD)//用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时保留
@Documented
public @interface RecordLog {

    /**
     * 方法描述 默认可以不填
     */
    String desc() default "";

    /**
     * 日志类型
     */
    LogType logType() default LogType.SELECT;

//    //增加个参数类 指定这个类是Convert的子类
//    Class<? extends Convert> convert();

}