package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyOthers extends ActivityConsumption{
    String itemName;
    public BuyOthers(int numberOfPurchase, String itemName){
        super(numberOfPurchase);
        this.itemName = itemName;
    }

    @Override
    public double getEmission() {
        return this.numberOfPurchase * EcoTrackerEmissionConstant.BUY_OTHER_EMISSION_PER_PURCHASE;
    }
}
