package strategy.quack;

public class MallaedDuck extends Duck{
    public MallaedDuck() {
        setQuackBehavior(new Quack());
        setFlyBehavior(new FlyWithWings());
    }

    @Override
    public void display() {
        System.out.println("MallaredDuck");
    }
}
