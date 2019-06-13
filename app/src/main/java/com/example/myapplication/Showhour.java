package com.example.myapplication;

public class Showhour {
    Integer hour;
    Integer minute;
    Boolean available;
    Boolean disable;

    public Showhour(Integer hour, Integer minute, Boolean available, Boolean disable) {
        this.hour = hour;
        this.minute = minute;
        this.available = available;
        this.disable = disable;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
