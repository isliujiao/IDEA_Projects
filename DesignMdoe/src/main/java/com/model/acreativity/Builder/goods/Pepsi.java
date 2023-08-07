package com.model.acreativity.Builder.goods;

import com.model.acreativity.Builder.server.abs.ColdDrink;

/**
 * @author 厚积薄发
 * @create 2023-05-06-16:08
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}