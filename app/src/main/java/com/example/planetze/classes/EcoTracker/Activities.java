package com.example.planetze.classes.EcoTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activities {
    public HashMap<Date, List<DailyActivity>> activities;

    public List<DailyActivity> getActivityByDate(Date date){
        return activities.get(date);
    }

    public List<DailyActivity> getActivityByDate(int day, int month, int year){
        return activities.get(new Date(day, month, year));
    }

    public void addActivity(Date date, DailyActivity activity){
        // Check if there's ana ctivity on date
        if(activities.containsKey(date)){
            activities.get(date).add(activity);
        } else {
            List<DailyActivity> newActivity = new ArrayList<DailyActivity>();
            newActivity.add(activity);
            activities.put(date, newActivity);
        }
    }

    public double calculateTotalEmissionByDate(Date date){
        List<DailyActivity> dailyActivities = activities.get(date);
        double totalEmission = 0;
        for(DailyActivity activity : dailyActivities){
            totalEmission += activity.getEmission();
        }
        return totalEmission;
    }

    public double calculateTotalEmissionByDate(int day, int month, int year){
        return calculateTotalEmissionByDate(new Date(day, month, year));
    }
}
