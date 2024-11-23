package com.example.planetze.classes.EcoTracker.Habit;

import com.example.planetze.classes.EcoTracker.Date;

public class ThermostatHabit extends Habit {

    public ThermostatHabit() {
        super(new Date());
        this.name = "Adjust Thermostat for Efficiency";
        this.description = "Set your thermostat to an energy-efficient temperature" +
                " to save on heating and cooling costs while reducing energy consumption.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }
    public ThermostatHabit(Date date) {
        super(date);
        this.name = "Adjust Thermostat for Efficiency";
        this.description = "Set your thermostat to an energy-efficient temperature" +
                " to save on heating and cooling costs while reducing energy consumption.";
        this.category = "Energy";
        this.impactLevel = "Medium";
    }

}
