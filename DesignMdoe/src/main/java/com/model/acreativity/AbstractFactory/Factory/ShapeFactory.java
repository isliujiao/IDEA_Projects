package com.model.acreativity.AbstractFactory.Factory;

import com.model.acreativity.AbstractFactory.service.Color;
import com.model.acreativity.AbstractFactory.service.Shape;
import com.model.acreativity.AbstractFactory.service.impl.Circle;
import com.model.acreativity.AbstractFactory.service.impl.Rectangle;
import com.model.acreativity.AbstractFactory.service.impl.Square;

/**扩展了 AbstractFactory 的工厂类
 * @author:厚积薄发
 * @create:2022-07-28-13:05
 */
public class ShapeFactory extends AbstractFactory{
    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
