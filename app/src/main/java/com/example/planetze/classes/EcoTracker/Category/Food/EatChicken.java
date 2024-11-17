package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatChicken extends ActivityFood{
    public EatChicken(int numberOfServings) {
        super(numberOfServings);
    }

    @Override
    public double getEmission() {
        return this.numberOfServings * EcoTrackerEmissionConstant.EAT_CHICKEN_EMISSION_PER_SERVING;
    }
}
