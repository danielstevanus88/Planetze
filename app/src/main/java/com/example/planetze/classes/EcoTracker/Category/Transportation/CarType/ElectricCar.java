package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class ElectricCar extends Car{

    public ElectricCar(){
        this.emissionPerKilometer = EcoTrackerEmissionConstant.DRIVE_PERSONAL_VEHICLE_ELECTRIC_EMISSION;
        this.displayCar = "Electric Car";
        this.carTypeId = EcoTrackerActivityConstant.ID_CAR_ELECTRIC;
    }
}
