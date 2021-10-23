package factory.MP05;

import java.awt.*;

public class RightTriangle extends Shape{
    public RightTriangle(Point[] points, String type) {
        super(points, type);
    }

    @Override
    public double calcArea() {
        double height =Math.abs(points[0].getY() - points[2].getY());
        double width = Math.abs(points[2].getX() - points[1].getX());

        return height * width /2 ;

    }
}
