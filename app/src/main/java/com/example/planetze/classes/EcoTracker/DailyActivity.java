package com.example.planetze.classes.EcoTracker;

import java.util.UUID;

public abstract class DailyActivity {

    private UUID uuid;

    public DailyActivity(){
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return this.uuid;
    }


    public abstract String getCategoryName();
    public abstract double getEmission();


}
