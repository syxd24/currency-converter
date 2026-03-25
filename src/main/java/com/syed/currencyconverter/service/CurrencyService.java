package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    public ConversionResponse convert(double amount, String from, String to) {
        double rate = getRate(from, to);
        double convertedAmount = amount * rate;

        return new ConversionResponse(amount, from, to, rate, convertedAmount);
    }

    private double getRate(String from, String to) {
        if (from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("EUR")) {
            return 0.92;
        }
        if (from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase("USD")) {
            return 1.09;
        }
        if (from.equalsIgnoreCase("USD") && to.equalsIgnoreCase("INR")) {
            return 83.10;
        }
        if (from.equalsIgnoreCase("INR") && to.equalsIgnoreCase("USD")) {
            return 0.012;
        }
        if (from.equalsIgnoreCase("EUR") && to.equalsIgnoreCase("INR")) {
            return 90.50;
        }
        if (from.equalsIgnoreCase("INR") && to.equalsIgnoreCase("EUR")) {
            return 0.011;
        }
        if (from.equalsIgnoreCase(to)) {
            return 1.0;
        }

        throw new IllegalArgumentException("Unsupported currency conversion: " + from + " to " + to);
    }
}