package com.example.javatest.controller;

/**
 * @author liujiao
 * @date 2023/9/7 17:51
 */
public class Singleton {

    private static Singleton single = null;

    private Singleton() {
    }

    public static Singleton getInstance(){
        if(null == single){
            single = new Singleton();
        }
        return single;
    }
}
