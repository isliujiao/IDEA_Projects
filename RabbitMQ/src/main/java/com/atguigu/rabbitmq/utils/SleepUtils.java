package com.atguigu.rabbitmq.utils;

/**
 * @author:厚积薄发
 * @create:2022-08-17-13:17
 */
public class SleepUtils {

    public static void sleep(int second) {
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }

}