package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatPork extends ActivityFood{
    public EatPork(int numberOfServings) {
        super(numberOfServings);
    }

    @Override
    public double getEmission() {
        return numberOfServings * EcoTrackerEmissionConstant.EAT_PORK_EMISSION_PER_SERVING;
    }
}
