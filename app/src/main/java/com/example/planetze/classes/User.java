package com.example.planetze.classes;

import java.util.HashMap;

public class User {
    public String uid;
    public String name;
    public String email;
    public HashMap<String, Integer> questionnaireAnswers;
    public String country;
    public User(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.questionnaireAnswers = new HashMap<>();
    }

    public User(){
        this.questionnaireAnswers = new HashMap<>();
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
}
