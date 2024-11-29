package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class Flight extends ActivityTransportation {
    public String type;
    public int num;

    public Flight(String type, int num) {
        this.type = type;
        this.num = num;
        // TODO: Calculate emission based on type
        this.emission = hour * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
        // TODO: Modify display text
        this.displayText = "Take " + type + " flight (" + num + ")";
    }
}