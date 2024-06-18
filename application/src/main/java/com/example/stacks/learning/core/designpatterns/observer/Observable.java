package com.example.stacks.learning.core.designpatterns.observer;

public interface Observable {

    void add(DisplayObserver observer);

    void remove(DisplayObserver observer);

    void notifyObservers();

    void changeState(Temperature newTemperature);

    Temperature getChangedState();
}
