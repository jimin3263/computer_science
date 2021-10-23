package oop.inheritance;

public class Child extends Parent {
    private int value;
    public Child(String s, int n) {
        super(s);
        value = n;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
