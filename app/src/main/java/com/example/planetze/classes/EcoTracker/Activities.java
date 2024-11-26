package com.example.planetze.classes.EcoTracker;

import com.example.planetze.classes.LoginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Activities {
    public HashMap<Date, List<DailyActivity>> activities;

    public Activities(){
        this.activities = new HashMap<>();
    }

    public Activities(HashMap<Date, List<DailyActivity>> activities){
        this.activities = activities;
    }
    public HashMap<Date, List<DailyActivity>> getActivities(){
        return this.activities;
    }

    public List<DailyActivity> getActivityByDate(Date date){
        return activities.get(date);
    }

    public List<DailyActivity> getActivityByDate(int day, int month, int year){
        return activities.get(new Date(day, month, year));
    }

    public void addActivity(Date date, DailyActivity activity){
        // Check if there's an activity on date
        if(activities.containsKey(date)){
            activities.get(date).add(activity);
        } else {
            List<DailyActivity> newActivity = new ArrayList<DailyActivity>();
            newActivity.add(activity);
            activities.put(date, newActivity);
        }
    }

    public void removeActivity(UUID uuid){
        for(Date date : activities.keySet()){
            List<DailyActivity> listDailyActivities = activities.get(date);
            for(DailyActivity activity : listDailyActivities){
                if(activity.getUuid().equals(uuid)){
                    listDailyActivities.remove(activity);
                    if(listDailyActivities.isEmpty()){
                        activities.remove(date);
                    }
                    return;
                }
            }
        }

    }

}