package com.model.bstructural.Decorator.server.decor;

import com.model.bstructural.Decorator.server.Shape;
import com.model.bstructural.Decorator.server.ShapeDecorator;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:16
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}