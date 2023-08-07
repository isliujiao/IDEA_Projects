package com.model.acreativity.AbstractFactory.Factory;

import com.model.acreativity.AbstractFactory.service.Color;
import com.model.acreativity.AbstractFactory.service.Shape;


/**抽象工厂
 * @author:厚积薄发
 * @create:2022-07-28-13:04
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);

}
