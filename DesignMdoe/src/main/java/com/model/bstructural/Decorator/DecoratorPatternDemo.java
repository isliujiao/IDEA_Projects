package com.model.bstructural.Decorator;

import com.model.bstructural.Decorator.server.Shape;
import com.model.bstructural.Decorator.server.ShapeDecorator;
import com.model.bstructural.Decorator.server.impl.Circle;
import com.model.bstructural.Decorator.server.impl.Rectangle;
import com.model.bstructural.Decorator.server.decor.RedShapeDecorator;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:16
 */
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}