package com.model.bstructural.Decorator.server;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:16
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw(){
        decoratedShape.draw();
    }
}