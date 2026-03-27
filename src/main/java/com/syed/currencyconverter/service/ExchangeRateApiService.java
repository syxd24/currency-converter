package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ExchangeRateApiResponse;
import com.syed.currencyconverter.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateApiService {

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    public double getRate(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();

        String url = baseUrl + "/latest?from=" + from + "&to=" + to;

        ExchangeRateApiResponse response = restTemplate.getForObject(url, ExchangeRateApiResponse.class);

        if (response == null || response.getRates() == null || !response.getRates().containsKey(to)) {
            throw new BadRequestException("Unable to fetch exchange rate for " + from + " to " + to);
        }

        return response.getRates().get(to);
    }
}