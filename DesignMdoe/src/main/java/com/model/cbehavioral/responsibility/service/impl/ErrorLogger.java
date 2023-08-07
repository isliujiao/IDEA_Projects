package com.model.cbehavioral.responsibility.service.impl;

import com.model.cbehavioral.responsibility.service.AbstractLogger;

/**
 * @author 厚积薄发
 * @create 2023-05-19-16:47
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}