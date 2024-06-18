package com.example.stacks.learning.core.designpatterns.strategy;

public class SportsDrivingStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Applying sports driving strategy");
    }
}
