package oop.inheritance;

public class Main {
    public static void main(String[] args) {
        Parent base = new Parent("ycho");
        Child derived = new Child("cho",2019);

        System.out.println(base.getName()); //ycho
        System.out.println(derived.getValue()); //2019

        Parent base2 = derived;
        System.out.println(base2.getName()); //cho
        //System.out.println(base2.getValue());
        ChildPlus1 test = new ChildPlus1("jm",2021);
        System.out.println(test.getValue());

    }
}
