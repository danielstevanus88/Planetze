package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Activities;
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.Transportation.ActivityTransportation;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

public class WalkHabit extends Habit{

    public WalkHabit(Date startDate) {
        super(startDate);
        User currentUser = LoginManager.getCurrentUser();
        Activities activities = currentUser.getActivities();

        // Filter by transportation
        activities = ActivitiesFilter.filterActivitiesByCategory(activities, ActivityTransportation.class);

        // Set the number of carbon emission saved per day based on average of previous day
        this.numberOfCarbonEmissionSavedPerDay =
                ActivitiesCalculator.getDailyEmissionAverage(activities)
                        - EcoTrackerEmissionConstant.CYCLING_OR_WALKING_EMISSION;
    }
}
