package singleton.singleton2;

public class Singleton {
    private static Singleton inst = new Singleton();
    //필요할 때 생성하지 않고 시작할 때 바로 생성
    //사용하지 않는데 생성?
    private Singleton() {
    }

    public static Singleton getInstance() {
        return inst;
    }
}

