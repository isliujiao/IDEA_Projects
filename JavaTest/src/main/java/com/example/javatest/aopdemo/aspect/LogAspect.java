package com.example.javatest.aopdemo.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.javatest.aopdemo.common.RecordLog;
import com.example.javatest.aopdemo.dto.SystemLog;
import com.example.javatest.aopdemo.service.Convert;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liujiao
 * @date 2023/8/4 14:12
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义一个切面
     * 1.定义一个切入点
     * 2.定义横切逻辑
     * 3.织入
     */

    //定义横切逻辑--环绕通知
    @Around("@annotation(recordLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RecordLog recordLog) throws Throwable {

        log.info("-----------日志信息：{}----------", recordLog.desc());
        log.info("-----------环绕通知开始----------");

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        log.info("----- 类名：{} ------", className);
        log.info("----- 方法名：{} ------", methodName);

        //请求的参数
        Object[] args = proceedingJoinPoint.getArgs();
        List<Object> collect = Arrays.stream(args).collect(Collectors.toList());
        log.info("----- 请求参数：{} ------", collect);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());

        //记录时间
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            System.out.println("执行结果：" + result.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常：" + throwable.getMessage());
        }

        Long time = System.currentTimeMillis() - start;

        //todo 插入数据库

        log.info("执行时间：{}",time);
        log.info("-----------环绕通知结束----------");
        return result;
    }

}
