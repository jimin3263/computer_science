package oop.interDefault;

public class ValueMain {
    public static void main(String[] args) {
        ValueImpl1 v1 = new ValueImpl1("ValueImpl1");
        ValueImpl2 v2 = new ValueImpl2();
        System.out.println(v1.getName());
        System.out.println(v2.getName()); //name
        IValue i1 = v1;
        IValue i2 = v2;
        System.out.println(i1.getValue()); //디폴트 함수 사용
        System.out.println(i2.getValue()); //override
    }
}
