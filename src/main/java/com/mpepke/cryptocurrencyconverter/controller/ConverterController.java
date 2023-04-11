package com.mpepke.cryptocurrencyconverter.controller;

import com.mpepke.cryptocurrencyconverter.service.exception.CryptocurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.CurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.controller.exception.RequestBodyIsNullException;
import com.mpepke.cryptocurrencyconverter.controller.request.ConversionRequest;
import com.mpepke.cryptocurrencyconverter.service.ConversionInformationService;
import com.mpepke.cryptocurrencyconverter.service.HistoryService;
import com.mpepke.cryptocurrencyconverter.service.exception.ExternalApiResponseInvalidException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/convert")
@RestController
@AllArgsConstructor
public class ConverterController {

    private final ConversionInformationService conversionInformationService;
    private final HistoryService historyService;


    @GetMapping
    public ResponseEntity<Double> convertToCurrency(@RequestBody ConversionRequest conversionRequest) throws CryptocurrencyNotSupportedException, CurrencyNotSupportedException, RequestBodyIsNullException, ExternalApiResponseInvalidException {

        if (conversionRequest.getCurrency() == null || conversionRequest.getCryptocurrency() == null || conversionRequest.getAmount() <= 0)
            throw new RequestBodyIsNullException();

        String cryptocurrency = conversionRequest.getCryptocurrency().toUpperCase();
        String currency = conversionRequest.getCurrency().toUpperCase();


        double spotPrice = conversionInformationService.getSpotPrice(cryptocurrency, currency);
        historyService.saveRequestedConversion(conversionRequest, spotPrice);

        return ResponseEntity.ok().body(conversionRequest.getAmount() * spotPrice);

    }


}
