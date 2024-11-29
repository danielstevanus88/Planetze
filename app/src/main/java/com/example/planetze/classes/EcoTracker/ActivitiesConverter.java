package com.example.planetze.classes.EcoTracker;

import java.util.HashMap;
import java.util.List;

public class ActivitiesConverter {


    public static HashMap<Date, List<DailyActivity>> getActivitiesWithClassDate(HashMap<String, List<DailyActivity>> activities){
        // Convert String to Date
        HashMap<Date, List<DailyActivity>> newActivities = new HashMap<>();
        for(String date : activities.keySet()){
            newActivities.put(new Date(date), activities.get(date));
        }
        return newActivities;
    }
}
