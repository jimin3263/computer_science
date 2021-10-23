package oop.interDefault;

public interface IValue {
    default public int getValue(){
        return 0;
    }
}

class ValueImpl1 implements IValue {
    private String name = "ValueImpl1";

    public ValueImpl1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

//getValue 구현
class ValueImpl2 implements IValue {
    private String name;

    public ValueImpl2() {
        name = "ValueImpl2";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getValue() {
        return 1;
    }
}


