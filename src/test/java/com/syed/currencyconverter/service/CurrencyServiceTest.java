package com.syed.currencyconverter.service;

import com.syed.currencyconverter.dto.ConversionResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Test
    void shouldConvertAmountSuccessfully() {
        ExchangeRateApiService exchangeRateApiService = mock(ExchangeRateApiService.class);
        when(exchangeRateApiService.getRate("EUR", "USD")).thenReturn(1.1);

        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        ConversionResponse response = currencyService.convert(100, "EUR", "USD");

        assertEquals(100, response.getAmount());
        assertEquals("EUR", response.getFrom());
        assertEquals("USD", response.getTo());
        assertEquals(1.1, response.getRate(), 0.0001);
        assertEquals(110.0, response.getConvertedAmount(), 0.0001);
    }

    @Test
    void shouldThrowExceptionWhenAmountIsZero() {
        ExchangeRateApiService exchangeRateApiService = mock(ExchangeRateApiService.class);
        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> currencyService.convert(0, "EUR", "USD")
        );

        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    @Test
    void shouldReturnSameAmountWhenCurrenciesAreSame() {
        ExchangeRateApiService exchangeRateApiService = mock(ExchangeRateApiService.class);
        CurrencyService currencyService = new CurrencyService(exchangeRateApiService);

        ConversionResponse response = currencyService.convert(100, "EUR", "EUR");

        assertEquals(100, response.getAmount());
        assertEquals("EUR", response.getFrom());
        assertEquals("EUR", response.getTo());
        assertEquals(1.0, response.getRate(), 0.0001);
        assertEquals(100.0, response.getConvertedAmount(), 0.0001);

        verify(exchangeRateApiService, never()).getRate(anyString(), anyString());
    }
}