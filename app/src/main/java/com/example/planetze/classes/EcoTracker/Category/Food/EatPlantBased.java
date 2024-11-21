package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatPlantBased extends ActivityFood{
    public EatPlantBased(int numberOfServings) {
        super(numberOfServings);
    }

    @Override
    public double getEmission() {
        return this.numberOfServings * EcoTrackerEmissionConstant.EAT_PLANT_BASED_EMISSION_PER_SERVING;
    }
}
