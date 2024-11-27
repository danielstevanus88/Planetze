package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyOthers extends ActivityConsumption{
    public BuyOthers(int numberOfPurchase, String itemName){
        this.itemName = itemName;
        this.numberOfPurchase = numberOfPurchase;
        this.displayText = "Buy " + itemName + " (" + numberOfPurchase +"x)";
        this.emission = this.numberOfPurchase * EcoTrackerEmissionConstant.BUY_OTHER_EMISSION_PER_PURCHASE;
    }


}
