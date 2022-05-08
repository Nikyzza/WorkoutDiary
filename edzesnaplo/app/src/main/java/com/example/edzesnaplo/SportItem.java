package com.example.edzesnaplo;

import java.util.ArrayList;
import java.util.List;

public class SportItem {
    private String name;
    private int workoutTime;
    private int kcal;
    private float km;
    public static final List<SportItem> WORKOUTS = new ArrayList<>();

    public SportItem(String name, int workoutTime, int kcal, float km) {
        this.name = name;
        this.workoutTime = workoutTime;
        this.kcal = kcal;
        this.km = km;
    }

    public SportItem() {}

    public String getName() {
        return name;
    }

    public int getWorkoutTime() {
        return workoutTime;
    }

    public int getKcal() {
        return kcal;
    }

    public float getKm() {
        return km;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (SportItem s : WORKOUTS) {
            builder.append(getName());
            builder.append(": elégetett kalória: ");
            builder.append(getKcal());
            builder.append(" kcal, időtartam: ");
            builder.append(getWorkoutTime());
            builder.append(" perc, távolság: ");
            builder.append(getKm());
            builder.append(" km");
        }

        return builder.toString();
    }
}
