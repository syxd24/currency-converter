package com.syed.currencyconverter.controller;

import com.syed.currencyconverter.dto.ConversionResponse;
import com.syed.currencyconverter.service.CurrencyService;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/api/convert")
    public ConversionResponse convertCurrency(
            @RequestParam @Positive double amount,
            @RequestParam String from,
            @RequestParam String to
    ) {
        return currencyService.convert(amount, from, to);
    }
}