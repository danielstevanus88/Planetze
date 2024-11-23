package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Activities;
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.Transportation.ActivityTransportation;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

public class WalkorBikeHabit extends Habit{

    public WalkorBikeHabit() {
        super(new Date());
        this.name = "Walk or Bike for Short Trips";
        this.description = "Opt for walking or biking instead of driving for" +
                " short distances to reduce fuel consumption and pollution.";
        this.category = "Transportation";
        this.impactLevel = "High";

    }
    public WalkorBikeHabit(Date startDate) {
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
