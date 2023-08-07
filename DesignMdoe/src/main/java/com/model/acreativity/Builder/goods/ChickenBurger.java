package com.model.acreativity.Builder.goods;

import com.model.acreativity.Builder.server.abs.Burger;

/**
 * 鸡肉汉堡包
 * chicken burger
 * @author 厚积薄发
 * @create 2023-05-06-16:07
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}