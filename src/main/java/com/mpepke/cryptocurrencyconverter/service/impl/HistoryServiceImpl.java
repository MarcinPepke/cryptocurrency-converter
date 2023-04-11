package com.mpepke.cryptocurrencyconverter.service.impl;

import com.mpepke.cryptocurrencyconverter.controller.request.ConversionRequest;
import com.mpepke.cryptocurrencyconverter.dto.HistoryDto;
import com.mpepke.cryptocurrencyconverter.model.CryptocurrencyEnum;
import com.mpepke.cryptocurrencyconverter.model.CurrencyEnum;
import com.mpepke.cryptocurrencyconverter.model.History;
import com.mpepke.cryptocurrencyconverter.repository.HistoryRepository;
import com.mpepke.cryptocurrencyconverter.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;


    @Override
    public HistoryDto saveRequestedConversion(ConversionRequest conversionRequest, double price) {
        History historyConversionToSave = History.builder()
                .cryptocurrency(CryptocurrencyEnum.valueOf(conversionRequest.getCryptocurrency().toUpperCase()))
                .currency(CurrencyEnum.valueOf(conversionRequest.getCurrency().toUpperCase()))
                .cryptocurrencyAmount(conversionRequest.getAmount())
                .totalAmount(conversionRequest.getAmount() * price)
                .amount(price).build();

        historyRepository.save(historyConversionToSave);
        return HistoryDto.builder()
                .amountTotal(historyConversionToSave.getTotalAmount())
                .amountPer(historyConversionToSave.getAmount())
                .currency(historyConversionToSave.getCurrency().toString())
                .cryptocurrency(historyConversionToSave.getCryptocurrency().toString())
                .id(historyConversionToSave.getId())
                .cryptocurrencyAmount(historyConversionToSave.getCryptocurrencyAmount()).build();
    }
}
