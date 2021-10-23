package factory.MP05;

import java.awt.*;

public abstract class Shape {
    String type;
    Point[] points;

    public Shape(Point[] points, String type) {
        this.points = points;
        this.type = type;
    }
    public abstract double calcArea();

    @Override
    public String toString() {
        String s;
        s = type + "\n";

        for (int i = 0; i < points.length; i++) {
            s += "P"+i+": "+points[i].toString()+"\n";
        }
        s += "area: "+ calcArea();
        return s;
    }
}
