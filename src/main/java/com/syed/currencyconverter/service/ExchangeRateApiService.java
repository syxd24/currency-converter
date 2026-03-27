package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ExchangeRateApiResponse;
import com.syed.currencyconverter.exception.ExchangeRateApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateApiService {

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public ExchangeRateApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getRate(String from, String to) {
        String url = baseUrl + "/latest?from=" + from + "&to=" + to;

        ExchangeRateApiResponse response = restTemplate.getForObject(url, ExchangeRateApiResponse.class);

        if (response == null || response.getRates() == null || !response.getRates().containsKey(to)) {
            throw new ExchangeRateApiException("Unable to fetch exchange rate for " + from + " to " + to);
        }

        return response.getRates().get(to);
    }
}