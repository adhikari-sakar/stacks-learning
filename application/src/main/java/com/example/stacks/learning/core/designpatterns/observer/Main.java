package com.example.stacks.learning.core.designpatterns.observer;

public class Main {

    public static void main(String[] args) {
        Observable stationObservable = new WeatherStationObservable();

        TVDisplayObserver tvDisplayObserver = new TVDisplayObserver(stationObservable);
        MobileDisplayObserver mobileDisplayObserver = new MobileDisplayObserver(stationObservable);

        stationObservable.add(tvDisplayObserver);
        stationObservable.add(mobileDisplayObserver);

        stationObservable.changeState(new Temperature(23.0));
        stationObservable.changeState(new Temperature(24.0));
        stationObservable.changeState(new Temperature(22.0));
    }
}
