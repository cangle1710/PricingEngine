package com.spothero.pricingengine.config;

import java.time.LocalTime;

public class TimeConfig {
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer price;

    public TimeConfig(LocalTime startTime, LocalTime endTime, int price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
