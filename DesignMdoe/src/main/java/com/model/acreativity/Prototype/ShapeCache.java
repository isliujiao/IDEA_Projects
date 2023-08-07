package com.model.acreativity.Prototype;

import com.model.acreativity.Prototype.server.Shape;
import com.model.acreativity.Prototype.server.impl.Circle;
import com.model.acreativity.Prototype.server.impl.Rectangle;
import com.model.acreativity.Prototype.server.impl.Square;

import java.util.Hashtable;

/**
 * 形状 缓存，存储
 * @author 厚积薄发
 * @create 2023-05-06-16:33
 */
public class ShapeCache {

    private static Hashtable<String, Shape> shapeMap = new Hashtable<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("创造型设计模式");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}