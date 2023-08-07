package com.model.acreativity.Factory.service.impl;

import com.model.acreativity.Factory.service.Shape;

/**
 * 正方形
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside ‘Square’正方形::draw() method.");
    }
}
