package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class TakePublicTransportation extends ActivityTransportation {
    public String type;

    public TakePublicTransportation(String type, double hour) {
        this.type = type;
        this.hour = hour;
        this.emission = hour * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
        this.displayText = "Take " + type + " (" + hour + "hr)";
    }
}