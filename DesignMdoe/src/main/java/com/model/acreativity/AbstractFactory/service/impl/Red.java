package com.model.acreativity.AbstractFactory.service.impl;

import com.model.acreativity.AbstractFactory.service.Color;

/**
 * @author:厚积薄发
 * @create:2022-07-28-13:02
 */
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red红色::fill() method.");
    }
}
