package factory.MP05;

import java.awt.*;

public class Trapezoid extends Shape{
    public Trapezoid(Point[] points, String type) {
        super(points, type);
    }

    @Override
    public double calcArea() {
        //(윗변 + 아랫변)* 높이 / 2
        double top = Math.abs(points[3].getX() - points[0].getX());
        double down = Math.abs(points[1].getX() - points[2].getX());
        double height = Math.abs(points[0].getY() - points[2].getY());
        return (top+down) * height / 2;
    }
}
