package com.example.planetze.classes.EcoBalance;

import java.util.HashMap;
import java.util.UUID;

public class Project {


    String uuid;
    String name;
    String shortDescription;
    String longDescription;
    String imageLink;
    double carbonCredits;
    double price;
    String location;
    public Project(){
        this.name = "";
        this.shortDescription = "";
        this.longDescription = "";
        this.imageLink = "";
        this.uuid = "";
        this.location = "";
    }

    public Project(String name, String location, String shortDescription, String longDescription, String imageLink, int carbonCredits, double price){
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageLink = imageLink;
        this.carbonCredits = carbonCredits;
        this.price = price;
        this.uuid = UUID.randomUUID().toString();
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }
    public String getName(){
        return name;
    }

    public double getCarbonCredits() {
        return carbonCredits;
    }

    public double getPrice() {
        return price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLocation() {
        return location;
    }
}
