package com.example.planetze.classes;

import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class User {
        public String uid;
        public String name;
        public String email;
        public HashMap<String, Integer> questionnaireAnswers;
        public String country;
        public HashMap<String, List<DailyActivity>> activities;

    public User(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.questionnaireAnswers = new HashMap<>();
        this.activities = new HashMap<>();
    }

    public User(){
        this.questionnaireAnswers = new HashMap<>();
        this.activities = new HashMap<>();
    }

    public void addQuestionnaireAnswer(String key, int answer){
        questionnaireAnswers.put(key, answer);
    }

    public void removeQuestionnaireAnswer(String key){
        questionnaireAnswers.remove(key);
    }

    public void setCountry(String country){
        this.country = country;
    }

    public boolean hasFilledQuestionnaires(){
        return this.questionnaireAnswers.get("q21") != null;
    }


    public HashMap<String, List<DailyActivity>> getActivities(){

        return this.activities;

    }


    public List<DailyActivity> getActivityByDate(int day, int month, int year){
        return activities.get((new Date(day, month, year)).toString());
    }

    public void addActivity(Date date, DailyActivity activity){
        // Check if there's an Activity on date
        if(activities.containsKey(date.toString())){
            activities.get(date.toString()).add(activity);
        } else {
            List<DailyActivity> newActivity = new ArrayList<>();
            newActivity.add(activity);
            activities.put(date.toString(), newActivity);
        }
    }

    public void removeActivity(UUID uuid){
        for(String date : activities.keySet()){
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

    
    public String getEmail(){
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getName(){
        return name;
    }





    
}
