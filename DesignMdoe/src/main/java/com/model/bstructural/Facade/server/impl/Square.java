package com.model.bstructural.Facade.server.impl;

import com.model.bstructural.Facade.server.Shape;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:27
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}