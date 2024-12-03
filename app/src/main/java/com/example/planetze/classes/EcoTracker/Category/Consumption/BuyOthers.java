package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class BuyOthers extends ActivityConsumption {
    public BuyOthers(String itemName, int numberOfPurchase) {
        this.itemName = itemName;
        this.numberOfPurchase = numberOfPurchase;
        this.displayText = "Buy " + itemName + " (" + numberOfPurchase + "x)";
        this.emission = this.numberOfPurchase * EcoTrackerEmissionConstant.BUY_OTHER_EMISSION_PER_PURCHASE;
        this.typeId = EcoTrackerActivityConstant.ID_BUY_OTHERS;
    }

}
