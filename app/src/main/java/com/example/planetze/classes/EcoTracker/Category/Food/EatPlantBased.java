package com.example.planetze.classes.EcoTracker.Category.Food;

import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;

public class EatPlantBased extends ActivityFood{
    public EatPlantBased(int numberOfServings) {
        this.numberOfServings = numberOfServings;
        this.emission = numberOfServings * EcoTrackerEmissionConstant.EAT_PLANT_BASED_EMISSION_PER_SERVING;
        this.displayText = "Eat Plant Based (" + numberOfServings + "x)";
        this.typeId = EcoTrackerActivityConstant.ID_EAT_PLANT_BASED;
    }
}
