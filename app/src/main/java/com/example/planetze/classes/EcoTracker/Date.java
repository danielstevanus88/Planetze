package com.example.planetze.classes.EcoTracker;

import android.os.Build;

import java.time.LocalDate;

public class Date implements Comparable<Date>{
    int day;
    int month;
    int year;

    // Constructor with empty parameters will set the date for today's date
    public Date(){
        LocalDate today = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.day = today.getDayOfMonth();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.month = today.getMonthValue();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.year = today.getYear();
        }
    }

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return this.day == date.day && this.month == date.month && this.year == date.year;
    }

    @Override
    public int hashCode(){
        return this.day + this.month * 31 + this.year * 366;
    }

    // compareTo will be used for comparing two dates. Useful for filter by range of date.
    @Override
    public int compareTo(Date date) {
        return (this.day - date.day) + (this.month - date.month) * 31 + (this.year - date.year)* 366;
    }
}
