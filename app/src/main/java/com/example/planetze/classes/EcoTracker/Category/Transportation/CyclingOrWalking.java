package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class CyclingOrWalking extends ActivityTransportation{
    public CyclingOrWalking (){
        this.emission = EcoTrackerEmissionConstant.CYCLING_OR_WALKING_EMISSION;
        this.displayText = "Cycling/Walking";
    }
}
