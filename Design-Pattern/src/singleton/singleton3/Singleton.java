package singleton.singleton3;

public class Singleton {
    //cpu cache에 저장하지 않고 메모리에 읽고 저장
    //th1, th2  cpu의 각각의 cache에 저장함 -> cache 무시 바로 메모리로 접근
    private volatile static Singleton inst;

    private Singleton() {
    }

    //DCL = Double Checking Locking
    public static Singleton getInstance(){
        if (inst== null){
            synchronized (Singleton.class){
                if (inst == null){
                    inst = new Singleton();
                }
            }
        }
        return inst;
    }
}
