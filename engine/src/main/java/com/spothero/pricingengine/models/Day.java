package com.spothero.pricingengine.models;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Day {
    INVALID("invalid"),
    MONDAY("mon"),
    TUESDAY("tues"),
    WEDNESDAY("wed"),
    THURSDAY("thurs"),
    FRIDAY("fri"),
    SATURDAY("sat"),
    SUNDAY("sun");

    private static final Map<String, Day> DAY_MAP;

    static {
        Map<String, Day> map = new ConcurrentHashMap<>();
        for (Day instance : Day.values()) {
            map.put(instance.getDay().toLowerCase(), instance);
        }
        DAY_MAP = Collections.unmodifiableMap(map);
    }

    private String day;

    Day(String day) {
        this.day = day;
    }

    public static Day get(String day) {
        if (!DAY_MAP.containsKey(day))
            return Day.INVALID;

        return DAY_MAP.get(day.toLowerCase());
    }

    public String getDay() {
        return this.day;
    }
}
