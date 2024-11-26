package com.example.planetze.classes;

import com.example.planetze.classes.EcoTracker.Activities;

import java.util.HashMap;
import java.util.List;

public class User {
    public String uid;
    public String name;
    public String email;
    public HashMap<String, List<String>> habit;
    public HashMap<String, Integer> questionnaireAnswers;
    public String country;

    public Activities activities;

    public User(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.questionnaireAnswers = new HashMap<>();
        this.habit = new HashMap<String, List<String>>();
    }

    public User(){
        this.questionnaireAnswers = new HashMap<>();
        this.habit = new HashMap<String, List<String>>();
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

    public Activities getActivities(){
        return this.activities;
    }

    public HashMap<String, List<String>> getHabit(){
        return this.habit;
    }
}
