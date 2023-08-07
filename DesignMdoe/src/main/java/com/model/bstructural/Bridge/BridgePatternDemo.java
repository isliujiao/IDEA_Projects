package com.model.bstructural.Bridge;

import com.model.bstructural.Bridge.server.Circle;
import com.model.bstructural.Bridge.server.Shape;
import com.model.bstructural.Bridge.server.impl.GreenCircle;
import com.model.bstructural.Bridge.server.impl.RedCircle;

/**
 * @author 厚积薄发
 * @create 2023-05-08-17:07
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}