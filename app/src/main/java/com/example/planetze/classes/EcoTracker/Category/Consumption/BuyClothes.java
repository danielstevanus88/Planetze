package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyClothes extends ActivityConsumption{
    public BuyClothes(int numberOfPurchase) {
        super(numberOfPurchase);
    }

    @Override
    public double getEmission() {
        return numberOfPurchase * EcoTrackerEmissionConstant.BUY_CLOTHES_EMISSION_PER_PURCHASE;
    }
}
