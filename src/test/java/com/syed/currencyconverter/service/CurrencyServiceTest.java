package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ConversionResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrencyServiceTest {

    @Test
    void shouldReturnSameAmountAndRateOneWhenCurrenciesAreSame() {
        ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService();
        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        ConversionResponse response = currencyService.convert(100, "USD", "USD");

        assertEquals(100, response.getAmount());
        assertEquals("USD", response.getFrom());
        assertEquals("USD", response.getTo());
        assertEquals(1.0, response.getRate());
        assertEquals(100, response.getConvertedAmount());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsZeroOrLess() {
        ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService();
        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        assertThrows(IllegalArgumentException.class, () -> currencyService.convert(0, "USD", "EUR"));
    }

    @Test
    void shouldNormalizeLowercaseCurrenciesToUppercase() {
        ExchangeRateApiService exchangeRateApiService = new ExchangeRateApiService();
        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        ConversionResponse response = currencyService.convert(100, "usd", "usd");

        assertEquals("USD", response.getFrom());
        assertEquals("USD", response.getTo());
        assertEquals(1.0, response.getRate());
        assertEquals(100, response.getConvertedAmount());
    }
}