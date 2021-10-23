package strategy.quack;

public class Duck {
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }

    public void performQuack(){
        if (quackBehavior != null){
            quackBehavior.quack();
        }
    }

    public void performFly(){
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }

    public void display(){
        System.out.println("test");
    }


}
