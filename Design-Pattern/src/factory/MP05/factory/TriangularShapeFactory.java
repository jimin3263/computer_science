package factory.MP05.factory;

import factory.MP05.RightTriangle;
import factory.MP05.Shape;
import factory.MP05.Triangle;

import java.awt.*;

public class TriangularShapeFactory implements ShapeFactory {

    Shape shape;
    @Override
    public Shape create(String type, Point[] points) {
        if(type.equals("Triangle")){
            shape = new Triangle(points, type);
        }
        else if (type.equals("RightTriangle")){
            shape = new RightTriangle(points, type);
        }
        return shape;
    }
}
