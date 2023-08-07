package com.model.acreativity.Singleton.hungry;

/**
 * 饿汉式
 * 在类初始化时，已经自行实例化
 * @author:厚积薄发
 * @create:2022-07-29-10:56
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
