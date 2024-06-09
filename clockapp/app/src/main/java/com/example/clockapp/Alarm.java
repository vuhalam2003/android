package com.example.clockapp;

public class Alarm {
    private int id;
    private int hour;
    private int minute;
    private String label;
    private boolean snoozeEnabled;
    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isSnoozeEnabled() {
        return snoozeEnabled;
    }

    public void setSnoozeEnabled(boolean snoozeEnabled) {
        this.snoozeEnabled = snoozeEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
