package factory.pizza.store;

import factory.pizza.Pizza;

public abstract class PizzaStore {
    void prepareToBoxing(Pizza pizza){
        pizza.prepare();
        pizza.bake();
        pizza.box();
        pizza.cut();
    }

    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        prepareToBoxing(pizza);
        return pizza;
    }

    abstract Pizza createPizza(String type);
}
