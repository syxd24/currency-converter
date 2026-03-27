package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ExchangeRateApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExchangeRateApiServiceTest {

    @Test
    void shouldReturnRateWhenApiResponseIsValid() {
        RestTemplate restTemplate = mock(RestTemplate.class);

        ExchangeRateApiResponse response = new ExchangeRateApiResponse();
        response.setRates(Map.of("USD", 1.1));

        when(restTemplate.getForObject(
                "https://api.frankfurter.app/latest?from=EUR&to=USD",
                ExchangeRateApiResponse.class
        )).thenReturn(response);

        ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService(restTemplate);
        ReflectionTestUtils.setField(exchangeRateApiService, "baseUrl", "https://api.frankfurter.app");

        double rate = exchangeRateApiService.getRate("EUR", "USD");

        assertEquals(1.1, rate, 0.0001);
    }
}