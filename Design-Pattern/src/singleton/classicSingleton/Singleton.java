package singleton.classicSingleton;

public class Singleton {
    private static Singleton uniqueInstance; //유일한 인스턴스 저장

    private Singleton(){} //private 하게 생성자 -> 객체 생성 못하도록

    public static Singleton getInstance(){
        if (uniqueInstance == null){ //이미 객체가 있다면
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
