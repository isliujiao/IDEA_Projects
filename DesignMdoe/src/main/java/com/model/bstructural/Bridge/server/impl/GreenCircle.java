package com.model.bstructural.Bridge.server.impl;

import com.model.bstructural.Bridge.server.DrawAPI;

/**
 * @author 厚积薄发
 * @create 2023-05-08-17:07
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}