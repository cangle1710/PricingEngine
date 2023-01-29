package com.spothero.pricingengine.controller;

import com.spothero.pricingengine.config.RateConfig;
import com.spothero.pricingengine.config.TimeConfig;
import com.spothero.pricingengine.models.Day;
import com.spothero.pricingengine.models.Price;
import com.spothero.pricingengine.repository.RateRepository;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static com.spothero.pricingengine.util.TimeUtil.getDayOfWeekRateMapping;
import static com.spothero.pricingengine.util.TimeUtil.parseZonedDateTime;

@RestController
public class PricingController {
    private static final String RATES_PATH = "/rates";
    private static final String PRICE_PATH = "/price";
    private static final String START_PARAM = "start";
    private static final String END_PARAM = "end";
    private final RateRepository repository;
    private final Price UNAVAILABLE_PRICE = new Price("unavailable");

    public PricingController(RateRepository repository) {
        this.repository = repository;
    }

    @GetMapping(RATES_PATH)
    RateConfig getCurrentConfig() {
        RateConfig config = repository.findAll().stream()
                .findFirst()
                .orElse(new RateConfig());

        return config;
    }

    @PutMapping(RATES_PATH)
    public @ResponseBody RateConfig newRate(@RequestBody RateConfig newRate) {
        repository.deleteAll();
        return repository.save(newRate);
    }

    @GetMapping(PRICE_PATH)
    public @ResponseBody Price getPrice(@RequestParam(START_PARAM) String start,
                                        @RequestParam(END_PARAM) String end) {
        RateConfig rateConfig = getCurrentConfig();

        //parse start and end
        ZonedDateTime startDateTime = parseZonedDateTime(start);
        ZonedDateTime endDateTime = parseZonedDateTime(end);

        if (!startDateTime.getDayOfWeek().equals(endDateTime.getDayOfWeek())) {
            return UNAVAILABLE_PRICE;
        }

        Day day = Day.valueOf(startDateTime.getDayOfWeek().name());

        Map<Day, List<TimeConfig>> ratesMapping = getDayOfWeekRateMapping(rateConfig);
        int validRates = 0;
        Price price = new Price();
        for (TimeConfig config : ratesMapping.get(day)) {
            if (config.getStartTime().isBefore(startDateTime.toLocalTime()) &&
                    config.getEndTime().isAfter(endDateTime.toLocalTime())) {
                validRates++;
                price.setPrice(config.getPrice().toString());
            }
        }

        if (validRates > 1) {
            return UNAVAILABLE_PRICE;
        }

        return validRates == 0 ? UNAVAILABLE_PRICE : price;
    }
}
