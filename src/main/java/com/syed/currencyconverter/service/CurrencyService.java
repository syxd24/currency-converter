package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private final ExchangeRateApiService exchangeRateApiService;

    public CurrencyService(ExchangeRateApiService exchangeRateApiService) {
        this.exchangeRateApiService = exchangeRateApiService;
    }

    public ConversionResponse convert(double amount, String from, String to) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        from = from.toUpperCase();
        to = to.toUpperCase();

        double rate;

        if (from.equals(to)) {
            rate = 1.0;
        } else {
            rate = exchangeRateApiService.getRate(from, to);
        }

        double convertedAmount = amount * rate;

        return new ConversionResponse(amount, from, to, rate, convertedAmount);
    }
}