package factory.MP05;

import java.awt.*;

public class Rectangle extends Shape{
    public Rectangle(Point[] points, String type) {
        super(points, type);
    }

    @Override
    public double calcArea() {
        return Math.abs(points[1].x - points[0].x) *Math.abs(points[0].y-points[1].y);
    }
}
