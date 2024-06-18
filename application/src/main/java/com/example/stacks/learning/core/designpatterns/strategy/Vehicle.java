package com.example.stacks.learning.core.designpatterns.strategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Vehicle {

    private final DriveStrategy strategy;

    public void drive() {
        strategy.drive();
    }
}
