package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatChicken extends ActivityFood{
    public EatChicken(int numberOfServings) {
        this.numberOfServings = numberOfServings;
        this.emission = numberOfServings * EcoTrackerEmissionConstant.EAT_CHICKEN_EMISSION_PER_SERVING;
        this.displayText = "Eat Chicken (" + numberOfServings + "x)";
    }

}
