package com.model.acreativity.Builder.hander;

import com.model.acreativity.Builder.server.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 进餐 - 带有之前定义的 Item 对象。
 * @author 厚积薄发
 * @create 2023-05-06-16:09
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    //计算价钱
    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    //展示商品属性
    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}