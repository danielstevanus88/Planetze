package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class RecycleHabit extends Habit {

    public RecycleHabit() {
        super(new Date());
        this.name = "Recycle Responsibly";
        this.description = "Sort recyclables properly and ensure that materials like" +
                " paper, plastic, and metal are recycled efficiently.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
    public RecycleHabit(Date date) {
        super(date);
        this.name = "Recycle Responsibly";
        this.description = "Sort recyclables properly and ensure that materials like" +
                " paper, plastic, and metal are recycled efficiently.";
        this.category = "Consumption";
        this.impactLevel = "Medium";
    }
}
