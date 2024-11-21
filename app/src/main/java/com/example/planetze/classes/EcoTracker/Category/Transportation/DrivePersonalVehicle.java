package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.Car;

public class DrivePersonalVehicle extends ActivityTransportation{
    double distance;
    Car car;
    public DrivePersonalVehicle(double distance, Car car){
        this.distance = distance;
        this.car = car;
    }

    @Override
    public double getEmission(){
        return car.getCO2perKilometer() * distance;
    }
}
