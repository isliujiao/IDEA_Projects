package com.model.bstructural.Bridge.server;

/**
 * 作画
 * @author 厚积薄发
 * @create 2023-05-08-17:07
 */
public interface DrawAPI {
    /**
     * 画圆
     * @param radius 半径
     * @param x x
     * @param y y
     */
    public void drawCircle(int radius, int x, int y);
}