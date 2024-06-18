package com.example.stacks.learning.core.designpatterns.strategy;

public class OffRoadDrivingStrategy implements DriveStrategy{

    @Override
    public void drive() {
        System.out.println("Applying off road driving strategy");
    }
}
