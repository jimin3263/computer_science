package strategy.quack;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("날개 난다");
    }
}
