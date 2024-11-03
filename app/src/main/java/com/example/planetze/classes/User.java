package com.example.planetze.classes;

public class User {
    public String uid;
    public String name;
    public String email;

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

}
