package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class DieselCar extends Car{
    public DieselCar(){
        this.emissionPerKilometer = EcoTrackerEmissionConstant.DRIVE_PERSONAL_VEHICLE_DIESEL_EMISSION;
        this.displayCar = "Diesel Car";
    }
}
