package com.syed.currencyconverter;

import com.syed.currencyconverter.dto.ConversionResponse;
import com.syed.currencyconverter.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrencyService currencyService;

    @Test
    void shouldReturn200ForValidConversionRequest() throws Exception {
        ConversionResponse response =
                new ConversionResponse(100.0, "USD", "EUR", 0.92, 92.0);

        when(currencyService.convert(100.0, "USD", "EUR")).thenReturn(response);

        mockMvc.perform(get("/api/convert")
                        .param("amount", "100")
                        .param("from", "USD")
                        .param("to", "EUR"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenAmountIsZero() throws Exception {
        mockMvc.perform(get("/api/convert")
                        .param("amount", "0")
                        .param("from", "EUR")
                        .param("to", "USD"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenFromCurrencyIsMissing() throws Exception {
        mockMvc.perform(get("/api/convert")
                        .param("amount", "100")
                        .param("to", "USD"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkWhenFromAndToCurrenciesAreSame() throws Exception {
        mockMvc.perform(get("/api/convert")
                        .param("amount", "100")
                        .param("from", "EUR")
                        .param("to", "EUR"))
                .andExpect(status().isOk());
    }
}