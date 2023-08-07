package com.model.cbehavioral.responsibility.service.impl;

import com.model.cbehavioral.responsibility.service.AbstractLogger;

/**
 * @author 厚积薄发
 * @create 2023-05-19-16:47
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}