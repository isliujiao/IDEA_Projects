package com.model.acreativity.AbstractFactory.service.impl;

import com.model.acreativity.AbstractFactory.service.Color;

/**
 * @author:厚积薄发
 * @create:2022-07-28-13:03
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green绿色::fill() method.");
    }
}
