package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class LeftoverHabit extends Habit {
    public LeftoverHabit() {
        super(new Date());
        this.name = "Embrace Leftovers";
        this.description = "Save and repurpose leftovers instead of throwing them away to reduce " +
                "food waste and save money.";
        this.category = "Food";
        this.impactLevel = "Low";
    }
    public LeftoverHabit(Date date) {
        super(date);
        this.name = "Eat Leftovers";
        this.description = "Don't throw away your leftovers! Eat them all!";
        this.category = "Food";
        this.impactLevel = "Low";
    }
}
