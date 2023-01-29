package com.spothero.pricingengine.config;

import com.spothero.pricingengine.models.Rate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "config")
public class RateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rate> rates;

    public RateConfig() {
    }

    RateConfig(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
