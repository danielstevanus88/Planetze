package com.example.planetze.classes.EcoTracker.Category.Consumption;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EnergyBill extends ActivityConsumption {

    public String type;
    public double amount;

    // TODO: implement this monthly instead of daily
    public EnergyBill(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.displayText = type + " energy bill (" + amount + ")";
        this.emission = amount * EcoTrackerEmissionConstant.ENERGY_BILL;
        this.typeId = EcoTrackerActivityConstant.ID_ENERGY_BILL;

    }

}
