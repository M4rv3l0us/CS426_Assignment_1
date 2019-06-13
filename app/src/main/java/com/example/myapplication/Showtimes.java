package com.example.myapplication;

import java.util.List;

public class Showtimes {
    Integer Date;
    Integer Month;
    Boolean available;

    public Showtimes(Integer date, Integer month, Boolean available) {
        Date = date;
        Month = month;
        this.available = available;
    }

    public Integer getDate() {
        return Date;
    }

    public void setDate(Integer date) {
        Date = date;
    }

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
