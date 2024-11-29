package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class SustainableProductHabit extends Habit {
    public SustainableProductHabit() {
        super(new Date());
        this.name = "Choose Sustainable Products";
        this.description = "Support brands that prioritize sustainability by" +
                " using eco-friendly materials and ethical practices.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
    public SustainableProductHabit(Date date) {
        super(date);
        this.name = "Choose Sustainable Products";
        this.description = "Support brands that prioritize sustainability by" +
                " using eco-friendly materials and ethical practices.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
}
