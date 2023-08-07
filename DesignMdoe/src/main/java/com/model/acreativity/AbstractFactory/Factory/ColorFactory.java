package com.model.acreativity.AbstractFactory.Factory;

import com.model.acreativity.AbstractFactory.service.Color;
import com.model.acreativity.AbstractFactory.service.Shape;
import com.model.acreativity.AbstractFactory.service.impl.Blue;
import com.model.acreativity.AbstractFactory.service.impl.Green;
import com.model.acreativity.AbstractFactory.service.impl.Red;

/**扩展了 AbstractFactory 的工厂类
 * @author:厚积薄发
 * @create:2022-07-28-13:06
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}