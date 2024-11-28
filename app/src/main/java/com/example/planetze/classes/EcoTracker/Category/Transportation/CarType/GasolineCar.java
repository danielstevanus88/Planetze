package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class GasolineCar extends Car {
    public GasolineCar(){
        this.emissionPerKilometer = EcoTrackerEmissionConstant.DRIVE_PERSONAL_VEHICLE_GASOLINE_EMISSION;
        this.displayCar = "Gasoline Car";
    }

}
