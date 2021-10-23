package factory.pizza.store;

import factory.pizza.NYStyleCheesePizza;
import factory.pizza.Pizza;

//입력받은 문자열에 맞는 피자 객체 반환
public class NYPizzaStore extends PizzaStore {

    Pizza pizza;

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")){
            pizza = new NYStyleCheesePizza();
        }

        return pizza;
    }
}
