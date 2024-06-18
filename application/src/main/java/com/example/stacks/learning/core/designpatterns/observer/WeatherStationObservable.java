package com.example.stacks.learning.core.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationObservable implements Observable {

    private Temperature currentTemperature;
    private final List<DisplayObserver> observers = new ArrayList<>();

    @Override
    public void add(DisplayObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remove(DisplayObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(DisplayObserver::update);
    }

    @Override
    public void changeState(Temperature newTemperature) {
        this.currentTemperature = newTemperature;
        notifyObservers();
    }

    public Temperature getChangedState() {
        return this.currentTemperature;
    }
}
