package com.example.planetze.classes.EcoTracker;

import com.example.planetze.classes.User;

import java.util.HashMap;
import java.util.List;

public class ActivitiesCalculator {
    public static double calculateTotalEmission(Activities activities){
        HashMap<Date, List<DailyActivity>> activitiesMap = activities.getActivities();
        
        double totalEmission = 0;
        for(Date date : activitiesMap.keySet()){
            List<DailyActivity> dailyActivities = activitiesMap.get(date);
            for(DailyActivity activity : dailyActivities){
                totalEmission += activity.getEmission();
            }
        }

        return totalEmission;
    }

    public static int getNumberOfDay(Activities activities){
        HashMap<Date, List<DailyActivity>> activitiesMap = activities.getActivities();
        return activitiesMap.size();
    }

    public static double getDailyEmissionAverage(Activities activities){
        return calculateTotalEmission(activities)/getNumberOfDay(activities);
    }
    
}
