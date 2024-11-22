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
    public FoodWasteHabit(Date date) {
        super(date);
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
