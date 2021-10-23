package oop.inheritance;

public class ChildPlus1 extends Child{

    public ChildPlus1(String s, int n) {
        super(s, n);
    }

    public int getValue(){
        return super.getValue() + 1;
    }
}
