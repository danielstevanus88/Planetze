package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

public class Car{
    public double CO2perKm;
    public String displayCar;
    public Car(){
        this.CO2perKm = getCO2perKilometer();
        this.displayCar = toString();
    }

    public  double getCO2perKilometer(){
        return this.CO2perKm;
    };

    public String toString(){
        return this.displayCar;
    }
}
