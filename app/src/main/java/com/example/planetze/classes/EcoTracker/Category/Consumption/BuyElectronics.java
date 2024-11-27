package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyElectronics extends ActivityConsumption{
    public BuyElectronics(int numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
        this.emission = numberOfPurchase * EcoTrackerEmissionConstant.BUY_ELECTRONICS_EMISSION_PER_PURCHASE;
        this.displayText = "Buy Electronics" + " (" + numberOfPurchase +"x)";
    }
}
