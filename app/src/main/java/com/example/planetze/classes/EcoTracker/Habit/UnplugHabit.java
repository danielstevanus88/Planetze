package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;
public class UnplugHabit extends Habit {
    public UnplugHabit() {
        super(new Date());
        this.name = "Unplug Electronics When Not in Use";
        this.description = "Save energy and reduce your carbon footprint by" +
                " unplugging electronics when not in use, such as chargers and appliances.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }
    public UnplugHabit(Date date) {
        super(date);
        this.name = "Unplug Electronics When Not in Use";
        this.description = "Save energy and reduce your carbon footprint by" +
                " unplugging electronics when not in use, such as chargers and appliances.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }
}
