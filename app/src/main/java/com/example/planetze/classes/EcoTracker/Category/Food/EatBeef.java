package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatBeef extends ActivityFood{

    public EatBeef(int numberOfServings) {
        this.numberOfServings = numberOfServings;
        this.emission = numberOfServings * EcoTrackerEmissionConstant.EAT_BEEF_EMISSION_PER_SERVING;
        this.displayText = "Eat Beef (" + numberOfServings + "x)";
        this.typeId = EcoTrackerActivityConstant.ID_EAT_BEEF;
    }

}
