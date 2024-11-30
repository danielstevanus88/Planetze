package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class CyclingOrWalking extends ActivityTransportation {
    public CyclingOrWalking(double distance) {
        this.distance = distance;
        this.emission = EcoTrackerEmissionConstant.CYCLING_OR_WALKING_EMISSION;
        this.displayText = "Cycling/Walking";
        this.typeId = EcoTrackerActivityConstant.ID_CYCLING_OR_WALKING;
    }
}
