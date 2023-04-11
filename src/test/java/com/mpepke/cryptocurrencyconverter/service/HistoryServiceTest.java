package com.mpepke.cryptocurrencyconverter.service;

import com.mpepke.cryptocurrencyconverter.controller.request.ConversionRequest;
import com.mpepke.cryptocurrencyconverter.model.CryptocurrencyEnum;
import com.mpepke.cryptocurrencyconverter.model.CurrencyEnum;
import com.mpepke.cryptocurrencyconverter.model.History;
import com.mpepke.cryptocurrencyconverter.repository.HistoryRepository;
import com.mpepke.cryptocurrencyconverter.service.impl.HistoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryServiceImpl historyService;

    private final ConversionRequest testRequest = ConversionRequest.builder()
            .cryptocurrency("BTC")
            .currency("USD")
            .amount(2.1)
            .build();

    private final double price = 542.2;
    private final History testHistory = History.builder()
            .cryptocurrency(CryptocurrencyEnum.BTC)
            .currency(CurrencyEnum.USD)
            .amount(price)
            .cryptocurrencyAmount(testRequest.getAmount())
            .totalAmount(price*testRequest.getAmount())
            .build();



    @Test
    public void saveConversionInformation_save(){
        historyService.saveRequestedConversion(testRequest,542.2);
        verify(historyRepository).save(eq(testHistory));
    }

}
