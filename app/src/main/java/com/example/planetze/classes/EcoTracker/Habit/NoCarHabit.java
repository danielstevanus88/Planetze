package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class NoCarHabit extends Habit {
    public NoCarHabit() {
        super(new Date());
        this.name = "No Car Usage";
        this.description = "Use public transportation, walk, or bike instead of driving" +
                " to reduce carbon emissions and environmental impact.";
        this.category = "Transportation";
        this.impactLevel = "High";
    }
    public NoCarHabit(Date date) {
        super(date);
        this.name = "No Car Usage";
        this.description = "Use public transportation, walk, or bike instead of driving" +
                " to reduce carbon emissions and environmental impact.";
        this.category = "Transportation";
        this.impactLevel = "High";
    }

}
