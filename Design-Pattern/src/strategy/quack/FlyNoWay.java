package strategy.quack;

public class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("안난다");
    }
}
