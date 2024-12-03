package com.example.planetze.classes.EcoTracker;

public class EcoTrackerEmissionConstant {

    // Transportation Category
    public static final double TAKE_PUBLIC_TRANSPORTATION_EMISSION = 1.3;
    public static final double CYCLING_OR_WALKING_EMISSION = 0;
    public static final double DRIVE_PERSONAL_VEHICLE_DIESEL_EMISSION = 0.27;
    public static final double DRIVE_PERSONAL_VEHICLE_ELECTRIC_EMISSION = 0.05;
    public static final double DRIVE_PERSONAL_VEHICLE_GASOLINE_EMISSION = 0.24;
    public static final double DRIVE_PERSONAL_VEHICLE_HYBRID_EMISSION = 0.16;


    // Food Category
    public static final double EAT_BEEF_EMISSION_PER_SERVING = 2500/365;
    public static final double EAT_PORK_EMISSION_PER_SERVING = 1450/365;
    public static final double EAT_CHICKEN_EMISSION_PER_SERVING = 950/365;
    public static final double EAT_FISH_EMISSION_PER_SERVING = 800/365;
    public static final double EAT_PLANT_BASED_EMISSION_PER_SERVING = 1000/365;

    // Consumption
    public static final double BUY_CLOTHES_EMISSION_PER_PURCHASE = 6.75; // Estimation (searched Google)
    public static final double BUY_ELECTRONICS_EMISSION_PER_PURCHASE = 300;
    public static final double BUY_OTHER_EMISSION_PER_PURCHASE = 20; // Average

}
