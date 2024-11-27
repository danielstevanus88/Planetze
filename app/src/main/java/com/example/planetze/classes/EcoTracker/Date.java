package com.example.planetze.classes.EcoTracker;

import java.time.LocalDate;

public class Date implements Comparable<Date>{
    int day;
    int month;
    int year;

    public Date(){
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Date should be in format "MM-DD-YYYY"
    public Date(String date){
        String[] dateArray = date.split("-");
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);
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

    public static Date today() {
        LocalDate currentDate = LocalDate.now();
        return new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    }

    public String toString(){
        return month+"-"+day+"-"+year;
    }
}
