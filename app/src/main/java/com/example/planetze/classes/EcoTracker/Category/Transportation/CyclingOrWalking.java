package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class CyclingOrWalking extends ActivityTransportation{
    @Override
    public double getEmission(){
        return EcoTrackerEmissionConstant.CYCLING_OR_WALKING_EMISSION;
    }
}
