package com.example.stacks.learning.core.designpatterns.observer;

public class MobileDisplayObserver implements DisplayObserver {

    private final Observable observable;

    public MobileDisplayObserver(Observable observable) {
        this.observable = observable;
    }

    @Override
    public void update() {
        Temperature temperature = observable.getChangedState();
        System.out.println("Observed data displaying on mobile " + temperature.value());
    }
}
