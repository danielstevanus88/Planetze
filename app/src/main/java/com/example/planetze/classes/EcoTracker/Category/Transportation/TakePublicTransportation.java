package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class TakePublicTransportation extends ActivityTransportation{
    double hour;
    public TakePublicTransportation(double hour){
        this.hour = hour;
    }
    @Override
    public double getEmission() {
        return this.hour * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
    }
}
