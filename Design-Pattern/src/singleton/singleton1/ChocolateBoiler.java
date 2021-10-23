package singleton.singleton1;


public class ChocolateBoiler {
    private static  ChocolateBoiler uniqueInstance;
    private boolean empty;
    private boolean boiled;

    private ChocolateBoiler() {
        this.empty = true;
        this.boiled = false;
    }

    //thread-safe 버전 synchronized 추가
    //여러 개의 thread -> getInstance 동시에 접근할 수도
    public static synchronized ChocolateBoiler getInstance(){
        if(uniqueInstance == null){
            //p1이 여기서 멈춘다면 -> p2도 들어온다면?
            //서로 다른 객체 두 개가 만들어짐
            uniqueInstance = new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    public boolean isEmpty(){
        return empty;
    }

    public boolean isBoiled(){
        return boiled;
    }

    public void fill(){
        if (isEmpty()){ //비어있다면
            empty = false;
            boiled = false;
            //보일러에 넢음
        }
    }
    //가득 차있고 끓으면 다음 단계
    public void drain(){
        if(!isEmpty() && isBoiled()){
            //다음 단계로 넘어감
            empty = true;
            boiled = false;
        }
    }
    //가득 차있고 끓지 않은 경우
    public void boil(){
        if (!isEmpty() && !isBoiled()){
            boiled = true; //끓인다
        }
    }

}
