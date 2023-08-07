package com.model.acreativity.Builder.server;

/**
 * 商品属性
 * @author 厚积薄发
 * @create 2023-05-06-15:59
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}