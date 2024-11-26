package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class PublicTransportHabit extends Habit {

    public PublicTransportHabit() {
        super(new Date());
        this.name = "Use Public Transportation";
        this.description = "Opt for buses, trains, or trams over driving to reduce" +
                " pollution and your carbon footprint.";
        this.category = "Transportation";
        this.impactLevel = "Medium";
    }
    public PublicTransportHabit(Date date) {
        super(date);
        this.name = "Use Public Transportation";
        this.description = "Opt for buses, trains, or trams over driving to reduce" +
                " pollution and your carbon footprint.";
        this.category = "Transportation";
        this.impactLevel = "Medium";
    }
}
