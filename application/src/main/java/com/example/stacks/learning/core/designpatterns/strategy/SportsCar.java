package com.example.stacks.learning.core.designpatterns.strategy;

public class SportsCar extends Vehicle {
    public SportsCar() {
        super(new SportsDrivingStrategy());
    }
}
