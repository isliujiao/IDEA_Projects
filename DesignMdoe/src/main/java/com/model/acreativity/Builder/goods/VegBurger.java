package com.model.acreativity.Builder.goods;

import com.model.acreativity.Builder.server.abs.Burger;

/**
 * 植物蛋白饼
 * @author 厚积薄发
 * @create 2023-05-06-16:07
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}