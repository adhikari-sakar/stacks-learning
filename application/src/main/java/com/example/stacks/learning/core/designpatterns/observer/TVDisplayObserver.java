package com.example.stacks.learning.core.designpatterns.observer;

public class TVDisplayObserver implements DisplayObserver {

    private final Observable observable;

    public TVDisplayObserver(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void update() {
        Temperature temperature = observable.getChangedState();
        System.out.println("Observed data displaying on TV " + temperature.value());
    }
}
