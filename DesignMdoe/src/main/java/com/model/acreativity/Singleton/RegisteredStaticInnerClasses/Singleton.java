package com.model.acreativity.Singleton.RegisteredStaticInnerClasses;

/**登记式/静态内部类
 * @author:厚积薄发
 * @create:2022-07-29-11:10
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
    }

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
