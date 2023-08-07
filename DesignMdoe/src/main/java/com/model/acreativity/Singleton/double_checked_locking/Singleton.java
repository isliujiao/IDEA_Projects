package com.model.acreativity.Singleton.double_checked_locking;

/**
 * 双检锁/双重校验锁
 */
public class Singleton {

    private static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
