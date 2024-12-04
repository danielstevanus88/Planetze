package com.example.planetze.classes.EcoTracker;

import com.example.planetze.classes.User;

import java.util.HashMap;
import java.util.List;

public class ActivitiesCalculator {
    public static double calculateTotalEmission(HashMap<Date, List<DailyActivity>> activitiesMap){
        
        double totalEmission = 0;
        for(Date date : activitiesMap.keySet()){
            List<DailyActivity> dailyActivities = activitiesMap.get(date);
            for(DailyActivity activity : dailyActivities){
                totalEmission += activity.getEmission();
            }
        }
        return totalEmission;
    }



    public static int getNumberOfDay(HashMap<Date, List<DailyActivity>> activitiesMap){
        return activitiesMap.size();
    }

    public static double getDailyEmissionAverage(HashMap<Date, List<DailyActivity>> activities){
        return calculateTotalEmission(activities)/getNumberOfDay(activities);
    }

    public static HashMap<String, Double> calculateEmissionsByCategory(HashMap<Date, List<DailyActivity>> dailyActivities) {

        // Initialize category totals
        double transportation = 0;
        double foodConsumption = 0;
        double consumptionAndShopping = 0;

        // Check if the input map is not null
        if (dailyActivities != null) {
            // Loop through the entries in the HashMap
            for (HashMap.Entry<Date, List<DailyActivity>> entry : dailyActivities.entrySet()) {
                List<DailyActivity> activities = entry.getValue(); // Get the list of DailyActivity for the current date

                // Loop through each activity in the list
                for (DailyActivity activity : activities) {
                    // Categorize and sum up emissions
                    switch (activity.getCategoryName()) {
                        case "Transportation":
                            transportation += activity.getEmission();
                            break;
                        case "Food":
                            foodConsumption += activity.getEmission();
                            break;
                        case "Consumption":
                            consumptionAndShopping += activity.getEmission();
                            break;
                    }
                }
            }
        }

        // Create the resulting map with calculated emissions by category
        HashMap<String, Double> emissionByCategory = new HashMap<>();
        emissionByCategory.put("Transportation", transportation);
        emissionByCategory.put("Food", foodConsumption);
        emissionByCategory.put("Consumption", consumptionAndShopping);

        return emissionByCategory;
    }

    public static int getCountOfActivitiesWithType(HashMap<Date, List<DailyActivity>> dailyActivities, int typeId){

        int count = 0;
        if (dailyActivities != null) {
            // Loop through the entries in the HashMap
            for (HashMap.Entry<Date, List<DailyActivity>> entry : dailyActivities.entrySet()) {
                List<DailyActivity> activities = entry.getValue(); // Get the list of DailyActivity for the current date

                // Loop through each activity in the list
                for (DailyActivity activity : activities) {
                    if(activity.getTypeId() == typeId){
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
