package com.model.bstructural.Facade;

import com.model.bstructural.Facade.server.Shape;
import com.model.bstructural.Facade.server.impl.Circle;
import com.model.bstructural.Facade.server.impl.Rectangle;
import com.model.bstructural.Facade.server.impl.Square;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:28
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}