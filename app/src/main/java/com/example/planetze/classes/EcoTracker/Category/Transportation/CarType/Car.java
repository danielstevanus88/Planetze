package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

import androidx.annotation.NonNull;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class Car{
    public double emissionPerKilometer;
    public String displayCar;
    public int carTypeId;
    public Car(){
        this.emissionPerKilometer = EcoTrackerEmissionConstant.DRIVE_PERSONAL_VEHICLE_DIESEL_EMISSION;
        this.displayCar = "Car";
        this.carTypeId = 1;
    }

    public  double getEmissionPerKilometer(){
        return this.emissionPerKilometer;
    }

    public String getDisplayCar(){
        return this.displayCar;
    }

    @NonNull
    public String toString(){
        return this.displayCar;
    }
}
