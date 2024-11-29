package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatPork extends ActivityFood{
    public EatPork(int numberOfServings) {
        this.numberOfServings = numberOfServings;
        this.emission = numberOfServings * EcoTrackerEmissionConstant.EAT_PORK_EMISSION_PER_SERVING;
        this.displayText = "Eat Pork (" + numberOfServings + "x)";
    }
}
