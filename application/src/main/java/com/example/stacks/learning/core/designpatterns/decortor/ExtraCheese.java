package com.example.stacks.learning.core.designpatterns.decortor;

public class ExtraCheese extends ToppingDecorator {

    private final BasePizza pizza;

    public ExtraCheese(BasePizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double cost() {
        return pizza.cost() + 2.0;
    }
}
