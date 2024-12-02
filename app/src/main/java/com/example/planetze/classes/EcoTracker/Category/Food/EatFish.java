package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatFish extends ActivityFood{
    public EatFish(int numberOfServings) {
        this.numberOfServings = numberOfServings;
        this.emission = numberOfServings * EcoTrackerEmissionConstant.EAT_FISH_EMISSION_PER_SERVING;
        this.displayText = "Eat Fish (" + numberOfServings + "x)";
        this.typeId = EcoTrackerActivityConstant.ID_EAT_FISH;
    }


}
