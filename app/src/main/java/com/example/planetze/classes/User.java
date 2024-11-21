package com.example.planetze.classes;

import com.example.planetze.classes.EcoTracker.Activities;

public class User {
    public String uid;
    public String name;
    public String email;

    public Activities activities;

    public User(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
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

    public Activities getActivities() {
        return activities;
    }

}
