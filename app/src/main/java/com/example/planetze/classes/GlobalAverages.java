package com.example.planetze.classes;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GlobalAverages {
    private static String [] countries;
    private static double[] averages;


    public static String[] getCountries() {
        return countries;
    }

    private static void setCountries(String[] countries) {
        GlobalAverages.countries = countries;
    }

    public static double[] getAverages() {
        return averages;
    }

    private static void setAverages(double[] averages) {
        GlobalAverages.averages = averages;
    }

    public static void initialize(Context context) {
        if (countries == null || averages == null) {
            extractData(context);
        }
    }

    private static void extractData(Context context) {
        List<String> countriesList = new ArrayList<>();
        List<Double> averagesList = new ArrayList<>();

        try (InputStream inputStream = context.getAssets().open("Global_Averages.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                countriesList.add(values[0]);
                try {
                    averagesList.add(Double.parseDouble(values[1]));
                } catch (NumberFormatException e) {
                    Log.e("CSVDataExtractor", "Error parsing average value: " + values[1], e);
                }

            }
        } catch (IOException e) {
            Log.e("CSVDataExtractor", "Error reading CSV file: Global_Averages.csv", e);
        }

        GlobalAverages.setCountries(countriesList.toArray(new String[0]));
        GlobalAverages.setAverages(averagesList.stream().mapToDouble(Double::doubleValue).toArray());
    }

    public static double getAverageOfCountry(String countryToFind){
        int index = 0;
        for(String country: countries){
            if(country.contains(countryToFind))
                break;
            index++;
        }

        return averages[index];
    }
    public static double getGlobalAverages(){
        double ret = 0;
        for (double average: averages){
            ret = ret + average / averages.length;
        }
        return ret;
    }
}