package com.spothero.pricingengine.util;


import com.spothero.pricingengine.config.RateConfig;
import com.spothero.pricingengine.config.TimeConfig;
import com.spothero.pricingengine.models.Day;
import com.spothero.pricingengine.models.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class TimeUtil {
    private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);
    private static final String COMMA_DELIMITER = ",";
    private static final String HYPHEN_DELIMITER = "-";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    public static List<Day> parseDays(String days) {
        if (days == null || days.isEmpty())
            return new ArrayList<>();
        List<Day> dayList = new ArrayList<>();
        for (String dayString : days.split(COMMA_DELIMITER)) {
            dayList.add(Day.get(dayString));
        }

        return dayList;
    }

    public static Map<Day, List<TimeConfig>> getDayOfWeekRateMapping(RateConfig rateConfig) {
        Map<Day, List<TimeConfig>> map = new ConcurrentHashMap<>();
        try {
            for (Rate rate : rateConfig.getRates()) {
                String[] times = rate.getTimes().split(HYPHEN_DELIMITER);
                for (Day day : parseDays(rate.getDays())) {
                    //parse times
                    LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("HHmm"));
                    LocalTime endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("HHmm"));

                    TimeConfig timeConfig = new TimeConfig(startTime, endTime, rate.getPrice());
                    List<TimeConfig> configs = map.getOrDefault(day, new ArrayList<>());
                    configs.add(timeConfig);
                    map.put(day, configs);
                }
            }
        } catch (final Exception ex) {
            log.error(ex.getMessage());
        }

        return Collections.unmodifiableMap(map);
    }

    public static ZonedDateTime parseZonedDateTime(String dateTimeString) {
        try {
            return ZonedDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format: " + dateTimeString);
        }
    }
}
