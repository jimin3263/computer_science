package oop.abs;

public class AbstractShapeMain {
    public static void main(String[] args) {
        Shape r = new Rectangle(20., 10.);
        Shape c = new Circle(10);

        System.out.println(r.calcArea());
        System.out.println(c.calcPerimeter());
    }
}
