package com.spothero.pricingengine.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String days;
    private String times;
    private String tz;
    private int price;

    Rate() {
    }

    Rate(String days, String times, String tz, int price) {
        this.days = days;
        this.times = times;
        this.tz = tz;
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "days='" + days + '\'' +
                ", times='" + times + '\'' +
                ", tz='" + tz + '\'' +
                ", price=" + price +
                '}';
    }
}
