package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.DailyActivity;

public abstract class ActivityConsumption extends DailyActivity {
    int numberOfPurchase;
    public ActivityConsumption(int numberOfPurchase){
        this.numberOfPurchase = numberOfPurchase;
    }
    @Override
    public String getCategoryName() {
        return "Consumption";
    }
}
