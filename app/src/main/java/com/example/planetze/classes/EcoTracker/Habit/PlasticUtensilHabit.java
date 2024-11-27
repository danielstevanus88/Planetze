package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class PlasticUtensilHabit extends Habit {

    public PlasticUtensilHabit() {
        super(new Date());
        this.name = "Avoid Single-Use Plastic Utensils";
        this.description = "Carry reusable utensils and avoid disposable plastic" +
                " cutlery to reduce plastic waste.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
    public PlasticUtensilHabit(Date date) {
        super(date);
        this.name = "Avoid Single-Use Plastic Utensils";
        this.description = "Carry reusable utensils and avoid disposable plastic" +
                " cutlery to reduce plastic waste.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
}
