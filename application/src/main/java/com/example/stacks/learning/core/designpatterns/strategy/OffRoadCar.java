package com.example.stacks.learning.core.designpatterns.strategy;

public class OffRoadCar extends Vehicle {
    public OffRoadCar() {
        super(new OffRoadDrivingStrategy());
    }
}
