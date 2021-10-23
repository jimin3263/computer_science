package singleton.singleton4;

public class Singleton {
    // inner static class
    private static class InnerSingleton {
        static final Singleton inst = new Singleton();
    }

    private Singleton() { }

    public static Singleton getInstance() {
        return InnerSingleton.inst;
    }
}
