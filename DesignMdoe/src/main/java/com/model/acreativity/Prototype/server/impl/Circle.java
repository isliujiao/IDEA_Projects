package com.model.acreativity.Prototype.server.impl;

import com.model.acreativity.Prototype.server.Shape;

/**
 * 圆形
 * @author 厚积薄发
 * @create 2023-05-06-16:32
 */
public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}