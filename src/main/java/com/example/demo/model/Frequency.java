package com.example.demo.model;

public enum Frequency {
    ONE_DAY(1440),
    ONE_HOUR(60),
    FIVE_MINS(5),
    FIFTH_MINS(15),
    FIVE_HOURS(300);

    public int minutes;

    private Frequency(int minutes){
        this.minutes = minutes;
    }

}
