package com.model.acreativity.Factory.service.impl;

import com.model.acreativity.Factory.service.Shape;

/**
 * 圆形
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside ‘Circle’圆形::draw() method.");
    }
}
