package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class TakePublicTransportation extends ActivityTransportation {

    public TakePublicTransportation(String type, double hour) {
        this.type = type;
        this.hour = hour;
        this.emission = hour * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
        this.displayText = "Take " + type + " (" + hour + "hr)";
        this.typeId = EcoTrackerActivityConstant.ID_TAKE_PUBLIC_TRANSPORTATION;
    }
}