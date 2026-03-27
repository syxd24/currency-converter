package com.syed.currencyconverter.dto;

import java.util.Map;

public class ExchangeRateApiResponse {

    private String base;
    private Map<String, Double> rates;

    public ExchangeRateApiResponse() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}