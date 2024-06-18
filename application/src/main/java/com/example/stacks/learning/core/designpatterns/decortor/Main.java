package com.example.stacks.learning.core.designpatterns.decortor;

public class Main {

    public static void main(String[] args) {
        BasePizza vegCheeseMushroomPizza = new ExtraCheese(new Mushroom(new VegPizza()));
        System.out.println(vegCheeseMushroomPizza.cost());

        BasePizza chickenCheeseMushroomPizza = new ExtraCheese(new Mushroom(new ChickenPizza()));
        System.out.println(chickenCheeseMushroomPizza.cost());
    }
}
