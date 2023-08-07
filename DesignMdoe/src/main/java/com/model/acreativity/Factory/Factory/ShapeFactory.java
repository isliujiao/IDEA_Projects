package com.model.acreativity.Factory.Factory;

import com.model.acreativity.Factory.service.impl.Circle;
import com.model.acreativity.Factory.service.impl.Rectangle;
import com.model.acreativity.Factory.service.Shape;
import com.model.acreativity.Factory.service.impl.Square;


/**
 */
public class ShapeFactory {
    /**
     * equalsIgnoreCase:
     * 将此字符串与另一个字符串进行比较，忽略大小写考虑。 如果两个字符串的长度相同，
     * 并且两个字符串中对应的字符相等，则忽略大小写。
     */
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }

}
