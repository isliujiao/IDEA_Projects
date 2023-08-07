package com.model.acreativity.Builder.server.abs;

import com.model.acreativity.Builder.server.Item;
import com.model.acreativity.Builder.server.Packing;
import com.model.acreativity.Builder.server.impl.Wrapper;

/**
 * 汉堡属性
 * @author 厚积薄发
 * @create 2023-05-06-16:01
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}