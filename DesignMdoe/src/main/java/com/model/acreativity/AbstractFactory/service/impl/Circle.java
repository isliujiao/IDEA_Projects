package com.model.acreativity.AbstractFactory.service.impl;

import com.model.acreativity.AbstractFactory.service.Shape;

/**圆形
 * @author:厚积薄发
 * @create:2022-07-28-12:40
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside ‘Circle’圆形::draw() method.");
    }
}
