package com.model.acreativity.AbstractFactory.service.impl;

import com.model.acreativity.AbstractFactory.service.Shape;

/**矩形，长方形
 * @author:厚积薄发
 * @create:2022-07-28-12:39
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside ‘Rectangle’长方形::draw() method.");
    }

}
