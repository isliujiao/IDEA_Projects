package com.model.acreativity.Prototype.server.impl;

import com.model.acreativity.Prototype.server.Shape;

/**
 * 矩形
 * @author 厚积薄发
 * @create 2023-05-06-16:28
 */
public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}