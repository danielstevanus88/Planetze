package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyElectronics extends ActivityConsumption{
    public BuyElectronics(int numberOfPurchase) {
        super(numberOfPurchase);
    }

    @Override
    public double getEmission() {
        return this.numberOfPurchase * EcoTrackerEmissionConstant.BUY_ELECTRONICS_EMISSION_PER_PURCHASE;
    }
}
