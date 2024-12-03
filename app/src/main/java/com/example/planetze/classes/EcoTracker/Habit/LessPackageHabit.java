package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class LessPackageHabit extends Habit {

    public LessPackageHabit() {
        super(new Date());
        this.name = "Reduce Packaging Waste";
        this.description = "Choose products with minimal packaging or packaging that" +
                " can be recycled to decrease waste.";
        this.category = "Consumption";
        this.impactLevel = "Low";
    }
    public LessPackageHabit(Date date) {
        super(date);
        this.name = "Purchase products with less packaging";
        this.description = "Excess packaging increases the rate of plastic being polluted into " +
                "the environment";
        this.category = "Consumption";
        this.impactLevel = "Low";
    }
}
