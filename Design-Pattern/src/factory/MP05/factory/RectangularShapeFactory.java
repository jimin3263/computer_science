package factory.MP05.factory;

import factory.MP05.Parallelogram;
import factory.MP05.Rectangle;
import factory.MP05.Trapezoid;

import java.awt.*;

public class RectangularShapeFactory implements ShapeFactory {
    factory.MP05.Shape shape;
    @Override
    public factory.MP05.Shape create(String type, Point[] points) {
        if (type.equals("Rectangle")){
            shape = new Rectangle(points,type);
        }
        else if(type.equals("Trapezoid")){
            shape = new Trapezoid(points, type);
        }
        else if(type.equals("Parallelogram")){
            shape = new Parallelogram(points, type);
        }
        return shape;
    }
}
