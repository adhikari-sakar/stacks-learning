package com.example.stacks.learning.core.designpatterns.decortor;

public class Mushroom extends ToppingDecorator {

    private final BasePizza pizza;

    public Mushroom(BasePizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double cost() {
        return pizza.cost() + 3.0;
    }
}
