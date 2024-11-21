package com.example.planetze.classes.EcoTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ActivitiesFilter {
    public static Activities filterActivitiesByRangeOfDate(Activities activities, Date from, Date to){
        HashMap<Date, List<DailyActivity>> activitiesMap = activities.getActivities();
        HashMap<Date, List<DailyActivity>> filteredActivities = new HashMap<>();

        for (HashMap.Entry<Date, List<DailyActivity>> entry : activitiesMap.entrySet()) {
            Date date = entry.getKey();
            List<DailyActivity> dailyActivities = entry.getValue();

            if(date.compareTo(from) >= 0 && date.compareTo(to) <= 0){
                filteredActivities.put(date, dailyActivities);
            }
        }

        return new Activities(filteredActivities);
    }

    // filter by category where category is checked by using instanceof a class
    public static <T extends DailyActivity> Activities filterActivitiesByCategory(Activities activities, Class<? extends T> category){
        HashMap<Date, List<DailyActivity>> activitiesMap = activities.getActivities();
        HashMap<Date, List<DailyActivity>> filteredActivities = new HashMap<>();

        for (HashMap.Entry<Date, List<DailyActivity>> entry : activitiesMap.entrySet()) {
            Date date = entry.getKey();
            List<DailyActivity> dailyActivities = entry.getValue();

            List<DailyActivity> filteredDailyActivities = new ArrayList<>();

            for(DailyActivity dailyActivity : dailyActivities){
                // if DailyActivity is instanceof subclass category class
                if(category.isInstance(dailyActivity)){
                    filteredDailyActivities.add(dailyActivity);
                }
            }

            filteredActivities.put(date, filteredDailyActivities);
        }

        return new Activities(filteredActivities);
    }
}
