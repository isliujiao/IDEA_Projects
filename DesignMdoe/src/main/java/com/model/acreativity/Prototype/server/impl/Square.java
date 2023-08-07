package com.model.acreativity.Prototype.server.impl;

import com.model.acreativity.Prototype.server.Shape;

/**
 * 正方形
 * @author 厚积薄发
 * @create 2023-05-06-16:31
 */
public class Square extends Shape {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}