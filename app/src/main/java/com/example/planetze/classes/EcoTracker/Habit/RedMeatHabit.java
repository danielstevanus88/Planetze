package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class RedMeatHabit extends Habit {

    public RedMeatHabit() {
        super(new Date());
        this.name = "Reduce Red Meat Consumption";
        this.description = "Limit red meat intake to decrease environmental" +
                " impact and improve overall health.";
        this.category = "Food";
        this.impactLevel = "High";
    }
    public RedMeatHabit(Date date) {
        super(date);
        this.name = "Reduce Red Meat Consumption";
        this.description = "Limit red meat intake to decrease environmental" +
                " impact and improve overall health.";
        this.category = "Food";
        this.impactLevel = "High";
    }

}
