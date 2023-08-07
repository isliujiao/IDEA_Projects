package com.model.bstructural.Decorator.server.impl;

import com.model.bstructural.Decorator.server.Shape;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:16
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}