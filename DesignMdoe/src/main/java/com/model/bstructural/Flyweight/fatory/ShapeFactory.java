package com.model.bstructural.Flyweight.fatory;

import com.model.bstructural.Flyweight.server.Shape;
import com.model.bstructural.Flyweight.server.impl.Circle;

import java.util.HashMap;

/**
 * @author 厚积薄发
 * @create 2023-05-17-10:44
 */
public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}