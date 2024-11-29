package com.example.planetze.classes.EcoTracker.Category.Transportation;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.Car;

public class DrivePersonalVehicle extends ActivityTransportation{
    public DrivePersonalVehicle(double distance, Car car){
        this.distance = distance;
        this.car = car;
        this.emission = car.getEmissionPerKilometer() * distance;
        this.displayText = "Drive " + car.toString() + " (" + distance + "km)";
    }
}
