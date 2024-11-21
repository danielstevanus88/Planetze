package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatFish extends ActivityFood{
    public EatFish(int numberOfServings) {
        super(numberOfServings);
    }

    @Override
    public double getEmission() {
        return this.numberOfServings * EcoTrackerEmissionConstant.EAT_FISH_EMISSION_PER_SERVING;
    }
}
