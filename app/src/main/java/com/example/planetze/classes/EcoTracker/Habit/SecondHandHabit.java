package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class SecondHandHabit extends Habit {
    public SecondHandHabit() {
        super(new Date());
        this.name = "Buy Second-Hand Products";
        this.description = "Choose second-hand items over new ones to promote" +
                " recycling, reduce waste, and save money.";
        this.category = "Consumption";
        this.impactLevel = "High";
    }
    public SecondHandHabit(Date date) {
        super(date);
        this.name = "Buy Second-Hand Products";
        this.description = "Choose second-hand items over new ones to promote" +
                " recycling, reduce waste, and save money.";
        this.category = "Consumption";
        this.impactLevel = "High";
    }
}
