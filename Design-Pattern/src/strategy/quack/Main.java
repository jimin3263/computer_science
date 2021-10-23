package strategy.quack;

public class Main {
    public static void main(String[] args) {
        Duck d = new MallaedDuck();

        d.performFly();
        d.display();
        d.performQuack();
    }
}
