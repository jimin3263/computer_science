package factory.MP05;

import java.awt.*;

public class Triangle extends Shape{

    public Triangle(Point[] points, String type) {
        super(points, type);
    }

    @Override
    public double calcArea() {
        double a,b,c,s;
        a = distance(points[0], points[1]);
        b = distance(points[2], points[1]);
        c = distance(points[2],points[0]);
        s = (a+b+c) /2;

        return Math.sqrt((s-a) * (s-b) * (s-c) * s);
    }
    private double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
}
