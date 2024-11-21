package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public abstract class  Habit {
    public Date startDate;
    public int numberOfTimesDone;
    public double numberOfCarbonEmissionSavedPerDay;

    public Habit (Date date){
        this.startDate = startDate;
        this.numberOfTimesDone = 0;
    }
}
