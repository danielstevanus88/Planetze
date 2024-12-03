package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class NoLightsHabit extends Habit{

    public NoLightsHabit() {
        super(new Date());
        this.name = "Turn Off Unnecessary Lights";
        this.description = "Save electricity by turning off lights when they are not in" +
                " use and using energy-efficient lighting.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }
    public NoLightsHabit(Date date) {
        super(date);
        this.name = "Turn Off Unnecessary Lights";
        this.description = "Save electricity by turning off lights when they are not in" +
                " use and using energy-efficient lighting.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }
}
