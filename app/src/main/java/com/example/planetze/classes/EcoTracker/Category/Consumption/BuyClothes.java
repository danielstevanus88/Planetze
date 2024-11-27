package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyClothes extends ActivityConsumption{
    public BuyClothes(int numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
        this.emission = numberOfPurchase * EcoTrackerEmissionConstant.BUY_CLOTHES_EMISSION_PER_PURCHASE;
        this.displayText = "Buy Clothes" + " (" + numberOfPurchase +"x)";
    }
}
