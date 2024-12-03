package com.example.planetze.classes.EcoTracker.Category.Transportation;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class Flight extends ActivityTransportation {

    public Flight(String type, int num) {
        this.type = type;
        this.numberOfFlights = num;
        // TODO: Calculate emission based on type
        this.emission = num * EcoTrackerEmissionConstant.TAKE_PUBLIC_TRANSPORTATION_EMISSION;
        // TODO: Modify display text
        this.displayText = "Take " + type + " flight (" + num + ")";

        this.typeId = EcoTrackerActivityConstant.ID_FLIGHT;
    }
}