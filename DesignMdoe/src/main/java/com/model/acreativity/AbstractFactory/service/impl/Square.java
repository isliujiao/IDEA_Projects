package com.model.acreativity.AbstractFactory.service.impl;

import com.model.acreativity.AbstractFactory.service.Shape;

/**正方形
 * @author:厚积薄发
 * @create:2022-07-28-12:40
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside ‘Square’正方形::draw() method.");
    }
}
