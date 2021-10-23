package oop.inter;

public class RectangleMain {
    public static void main(String[] args) {
        IShape r = new RectangleImpl(10.,20.);
        System.out.println(r.calcArea());
    }
}
