package com.example.stacks.learning.core.designpatterns.strategy;

public class Main {

    public static void main(String[] args) {
        OffRoadCar offRoadCar = new OffRoadCar();
        offRoadCar.drive();
        SportsCar sportsCar = new SportsCar();
        sportsCar.drive();
    }

}
