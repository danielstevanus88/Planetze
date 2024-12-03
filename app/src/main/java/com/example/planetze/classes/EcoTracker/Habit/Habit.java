package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public abstract class Habit {
    public String name;
    public String description;
    public Date startDate;
    public int numberOfTimesDone;
    public double numberOfCarbonEmissionSavedPerDay;
    public String category;
    public String impactLevel;

    public Habit (Date date){
        this.startDate = date;
        this.numberOfTimesDone = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImpactLevel(){
        return impactLevel;
    }

}
