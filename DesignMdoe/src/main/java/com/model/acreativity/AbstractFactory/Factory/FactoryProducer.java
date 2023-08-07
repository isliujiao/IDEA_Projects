package com.model.acreativity.AbstractFactory.Factory;

/**
 * @author:厚积薄发
 * @create:2022-07-28-13:08
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {
        //选择是使用形状对象还是颜色对象
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
