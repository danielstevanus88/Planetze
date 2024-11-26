package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class VeganHabit extends Habit{
    public VeganHabit() {
        super(new Date());
        this.name = "Follow a Vegan Diet";
        this.description = " Adopt a vegan diet to reduce your environmental" +
                " impact, including your carbon footprint, water usage, and land use.";
        this.category = "Food";
        this.impactLevel = "High";
    }
    public VeganHabit(Date date) {
        super(date);
        this.name = "Follow a Vegan Diet";
        this.description = " Adopt a vegan diet to reduce your environmental" +
                " impact, including your carbon footprint, water usage, and land use.";
        this.category = "Food";
        this.impactLevel = "High";
    }
}
