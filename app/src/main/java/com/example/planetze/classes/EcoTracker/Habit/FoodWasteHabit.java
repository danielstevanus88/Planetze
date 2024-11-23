package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Activities;
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.Food.ActivityFood;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

public class FoodWasteHabit extends Habit{
    public FoodWasteHabit() {
        super(new Date());
        this.name = "Minimize Food Waste";
        this.description = "Aim to reduce food waste by planning meals";
        this.category = "Food";
        this.impactLevel = "Low";
    }
    public FoodWasteHabit(Date date) {
        super(date);
        this.name = "No Food Waste";
        this.description = "No Food Waste";
        this.category = "Food";
        this.impactLevel = "Low";

        User currentUser = LoginManager.getCurrentUser();
        Activities activities = currentUser.getActivities();

        // Filter by Food
        activities = ActivitiesFilter.filterActivitiesByCategory(activities,
                ActivityFood.class);

        // Set the number of carbon emission saved per day based on average of previous day
        this.numberOfCarbonEmissionSavedPerDay =
                ActivitiesCalculator.getDailyEmissionAverage(activities)
                        - EcoTrackerEmissionConstant.CYCLING_OR_WALKING_EMISSION;
    }
}
