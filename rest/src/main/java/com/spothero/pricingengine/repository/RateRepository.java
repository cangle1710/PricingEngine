package com.spothero.pricingengine.repository;

import com.spothero.pricingengine.config.RateConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<RateConfig, Long> {
}