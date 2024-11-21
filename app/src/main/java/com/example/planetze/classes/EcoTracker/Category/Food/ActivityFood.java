package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.DailyActivity;

public abstract class ActivityFood extends DailyActivity {
    int numberOfServings;
    public ActivityFood(int numberOfServings){
        this.numberOfServings = numberOfServings;
    }
    @Override
    public String getCategoryName() {
        return "Food";
    }
}
