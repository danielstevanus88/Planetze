package com.example.planetze.classes.EcoTracker;

import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.Car;

import java.io.Serializable;
import java.util.UUID;

public class DailyActivity implements Serializable {

    // This class is not abstract as firebase cant desrialize if it's abstract

    // Field: Unique ID
    public String uuid;

    // Field: Id associated with the type of the activity. This is to help firebase deserializing
    // the daily activity type
    public int typeId;


    // Fields: Basic
    public String categoryName;
    public double emission;

    // Fields: Transportation Category
    public Car car;
    public String displayText;
    public double distance;
    public double hour;
    public String type;
    public int numberOfFlights;

    // Fields: Consumption Category
    public int numberOfPurchase;
    public String itemName;

    // Fields: Food Category
    public int numberOfServings;


    public DailyActivity(){
        this.uuid = UUID.randomUUID().toString();
    }


    public String getUuid() {
        return this.uuid;
    }


    public String getCategoryName(){
        return this.categoryName;
    };


    public double getEmission(){
        return this.emission;
    };


    public String toString(){
        return this.displayText;
    }


    public int getTypeId() {
        return  this.typeId;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public String getItemName() {
        return itemName;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public int getNumberOfPurchase() {
        return numberOfPurchase;
    }

    public double getDistance() {
        return distance;
    }

    public double getHour() {
        return hour;
    }

    public Car getCar() {
        return car;
    }

    public String getType() {
        return type;
    }
}
