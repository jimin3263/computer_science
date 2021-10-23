package factory.MP05.factory;

import factory.MP05.Shape;

import java.awt.*;

public interface ShapeFactory {
    Shape create(String rectangle, Point[] rectangle_points);
}
