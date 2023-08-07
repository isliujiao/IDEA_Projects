package com.model.acreativity.Factory.service.impl;

import com.model.acreativity.Factory.service.Shape;

/**
 * 矩形，长方形
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside ‘Rectangle’长方形::draw() method.");
    }

}
