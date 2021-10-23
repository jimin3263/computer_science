package oop.inter;

interface IShape {
    public double calcArea();
    public double calcPerimeter();
}

class RectangleImpl implements IShape{
    private double width, height;

    public RectangleImpl(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width*height;
    }

    @Override
    public double calcPerimeter() {
        return 2*(width + height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
