package com.example.planetze.classes.EcoTracker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.text.SimpleDateFormat;

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

    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setDay(int day){
        this.day = day;
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
    public int compareTo(Date other) {
        // Calculate the total number of days for this date
        int thisTotalDays = calculateDaysSinceStart();
        // Calculate the total number of days for the other date
        int otherTotalDays = other.calculateDaysSinceStart();

        // Return the difference
        return thisTotalDays - otherTotalDays;
    }

    public Date getOneDayBefore() {
        // If it is the first day of the month
        if (day == 1) {
            // Move to the previous month
            int newMonth = (month == 1) ? 12 : (month - 1);
            int newYear = (month == 1) ? (year - 1) : year;
            int newDay = getDaysInMonth(newMonth, newYear); // Get the last day of the previous month

            return new Date(newDay, newMonth, newYear);
        } else {
            // Just subtract one day
            return new Date(day - 1, month, year);
        }
    }

    private int calculateDaysSinceStart() {
        int totalDays = 0;

        // Add days for the years
        for (int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }

        // Add days for the months in the current year
        for (int i = 1; i < month; i++) {
            totalDays += getDaysInMonth(i, year);
        }

        // Add days in the current month
        totalDays += day;

        return totalDays;
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static String getDateAfterNDays(int days) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.add(Calendar.DAY_OF_YEAR, days);
        return dateFormat.format(calendar.getTime());
    }

    public static Date today() {
        LocalDate currentDate = LocalDate.now();
        return new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
    }

    public String toString(){
        return month+"-"+day+"-"+year;
    }


}
