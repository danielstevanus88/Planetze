package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyElectronics extends ActivityConsumption {
    public String type;

    public BuyElectronics(String type, int numberOfPurchase) {
        this.type = type;
        this.numberOfPurchase = numberOfPurchase;
        this.emission = numberOfPurchase * EcoTrackerEmissionConstant.BUY_ELECTRONICS_EMISSION_PER_PURCHASE;
        this.displayText = "Buy Electronics" + " (" + numberOfPurchase + "x)";
    }
}
