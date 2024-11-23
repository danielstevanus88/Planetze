package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class WaterHabit extends Habit {
    public WaterHabit() {
        super(new Date());
        this.name = "Save Water";
        this.description = "Practice water conservation by taking shorter" +
                " showers, fixing leaks, and using water-efficient appliances.";
        this.category = "Consumption";
        this.impactLevel = "High";
    }
    public WaterHabit(Date date) {
        super(date);
        this.name = "Save Water";
        this.description = "Practice water conservation by taking shorter" +
                " showers, fixing leaks, and using water-efficient appliances.";
        this.category = "Consumption";
        this.impactLevel = "High";
    }
}
