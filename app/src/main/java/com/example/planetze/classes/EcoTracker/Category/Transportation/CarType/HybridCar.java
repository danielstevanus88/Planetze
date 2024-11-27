package com.example.planetze.classes.EcoTracker.Category.Transportation.CarType;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class HybridCar extends Car{
    @Override
    public double getCO2perKilometer() {
        return EcoTrackerEmissionConstant.DRIVE_PERSONAL_VEHICLE_HYBRID_EMISSION;
    }

    @Override
    public String toString(){
        return "Hybrid Car";
    }
}
