package oop.poly;

import java.util.ArrayList;

public class shapeTagMain {
    public static void main(String[] args) {
        ShapeTag s1 = new ShapeTag("shape1");
        ShapeTag s2 = new ShapeTag("shape2");

        RectangleTag r = new RectangleTag("shape", "rectangle");
        CircleTag c = new CircleTag("shape","circle");

        System.out.println("Shape1 Tag: "+ s1);
        System.out.println("Shape2 Tag: "+ s2);
        System.out.println("Rectangle Tags: "+ r);
        System.out.println("Circle Tags: "+c);

        s1 = r;
        s2 = c;

        System.out.println(s1);
        System.out.println(s2); // child 객체를 출력 함 -> 부모 클래스로 선언된 변수에서 자식 클래스에서 오버라이딩 된 함수 호출됨
        //부모에게서 오버라이딩한 자식의 함수를 실질적으로 출력

        ArrayList list = new ArrayList();
        list.add(new ShapeTag("test1"));
        list.add(new ShapeTag("test2"));
        list.add(new RectangleTag("shape","rectaangle"));
        list.add(new CircleTag("shape","circle"));

        System.out.println("==start==");
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }
}
