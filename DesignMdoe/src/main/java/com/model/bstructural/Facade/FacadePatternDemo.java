package com.model.bstructural.Facade;

/**
 * @author 厚积薄发
 * @create 2023-05-09-17:28
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}