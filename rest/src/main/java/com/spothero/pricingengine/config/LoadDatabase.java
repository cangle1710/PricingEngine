package com.spothero.pricingengine.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spothero.pricingengine.repository.RateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final String DEFAULT_RATE_FILE_NAME = "default_rate.json";
    private Gson gson = new Gson();

    @Bean
    boolean initDatabase(RateRepository repository) {
        Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_RATE_FILE_NAME);
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_RATE_FILE_NAME)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            RateConfig rateConfig = gson.fromJson(jsonString, RateConfig.class);
            log.info("Preloading " + repository.save(rateConfig));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
