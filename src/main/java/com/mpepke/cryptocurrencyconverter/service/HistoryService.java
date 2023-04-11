package com.mpepke.cryptocurrencyconverter.service;


import com.mpepke.cryptocurrencyconverter.controller.request.ConversionRequest;
import com.mpepke.cryptocurrencyconverter.dto.HistoryDto;

public interface HistoryService {


    /**
     * save information about conversion to the database
     * @param conversionRequest
     * request received from user
     * @param price
     * current price of the cryptocurrency
     * @return
     * dto of the saved information about conversion
     */
    HistoryDto saveRequestedConversion(final ConversionRequest conversionRequest, double price);
}
