package com.model.acreativity.Builder.hander;

import com.model.acreativity.Builder.goods.ChickenBurger;
import com.model.acreativity.Builder.goods.Coke;
import com.model.acreativity.Builder.goods.Pepsi;
import com.model.acreativity.Builder.goods.VegBurger;

/**
 * 实际的 builder 类负责创建 Meal 对象。
 * @author 厚积薄发
 * @create 2023-05-06-16:09
 */
public class MealBuilder {

    /**
     * 准备素食进餐
     */
    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * 准备鸡肉汉堡进餐
     */
    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}