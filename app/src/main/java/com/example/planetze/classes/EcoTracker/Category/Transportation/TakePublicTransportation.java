package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class TakePublicTransportation extends ActivityTransportation{
    public TakePublicTransportation(double hour){
        this.hour = hour;
        this.emission = hour * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
        this.displayText = "Public Transportation (" + hour + "hr)";
    }
}
