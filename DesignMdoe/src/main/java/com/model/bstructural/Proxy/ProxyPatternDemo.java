package com.model.bstructural.Proxy;

import com.model.bstructural.Proxy.server.Image;

/**
 * @author 厚积薄发
 * @create 2023-05-17-11:04
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}