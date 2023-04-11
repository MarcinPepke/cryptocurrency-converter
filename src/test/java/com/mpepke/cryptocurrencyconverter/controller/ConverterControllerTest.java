package com.mpepke.cryptocurrencyconverter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpepke.cryptocurrencyconverter.controller.request.ConversionRequest;
import com.mpepke.cryptocurrencyconverter.dto.HistoryDto;
import com.mpepke.cryptocurrencyconverter.service.impl.ConversionInformationServiceImpl;
import com.mpepke.cryptocurrencyconverter.service.impl.HistoryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ConverterControllerTest {

    @MockBean
    private HistoryServiceImpl historyService;
    @MockBean
    private ConversionInformationServiceImpl conversionInformationService;

    @Autowired
    private MockMvc mvc;

    private final ConversionRequest testRequest = ConversionRequest.builder()
            .cryptocurrency("ETH")
            .currency("USD")
            .amount(3.2)
            .build();
    private final double price = 854.2;

    private final HistoryDto testHistory = HistoryDto.builder()
            .cryptocurrency("ETH")
            .currency("USD")
            .amountPer(price)
            .cryptocurrencyAmount(testRequest.getAmount())
            .amountTotal(price * testRequest.getAmount())
            .build();

    @Test
    @DisplayName("GET /api/convert success with actual data")
    public void getTotalAmountOfCryptocurrency() throws Exception {

        when(historyService.saveRequestedConversion(testRequest, price)).thenReturn(testHistory);
        when(conversionInformationService.getSpotPrice(testRequest.getCryptocurrency(), testRequest.getCurrency())).thenReturn(testHistory.getAmountTotal());

        MvcResult mvcResult = mvc.perform(get("/api/convert").content(requestToJson(testRequest)).header("Content-type", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        double expectedPrice = testHistory.getCryptocurrencyAmount() * testHistory.getAmountTotal();
        Assertions.assertEquals(expectedPrice, Double.parseDouble(result));

    }

    @Test
    @DisplayName("GET /api/convert failed with null body")
    public void getTotalAmountOfCryptocurrency_whenEmptyRequestBody_thenNotAcceptable() throws Exception {

        ConversionRequest test = new ConversionRequest();
        mvc.perform(get("/api/convert").content(requestToJson(test))
                        .header("Content-type", "application/json"))
                .andExpect(status().isNotAcceptable());

    }

    public static String requestToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
