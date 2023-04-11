package com.mpepke.cryptocurrencyconverter.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpepke.cryptocurrencyconverter.service.exception.CurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.model.CurrencyEnum;
import com.mpepke.cryptocurrencyconverter.service.exception.CryptocurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.dto.ConversionInformationDto;
import com.mpepke.cryptocurrencyconverter.model.CryptocurrencyEnum;
import com.mpepke.cryptocurrencyconverter.service.ConversionInformationService;
import com.mpepke.cryptocurrencyconverter.service.exception.ExternalApiResponseInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ConversionInformationServiceImpl implements ConversionInformationService {


    @Override
    public double getSpotPrice(String crypto, String currency) throws CryptocurrencyNotSupportedException, CurrencyNotSupportedException, ExternalApiResponseInvalidException {

        Arrays.stream(CryptocurrencyEnum.values()).filter(cryptoEnum -> cryptoEnum.toString().equals(crypto)).findAny().orElseThrow(CryptocurrencyNotSupportedException::new);
        Arrays.stream(CurrencyEnum.values()).filter(currencyEnum -> currencyEnum.toString().equals(currency)).findAny().orElseThrow(CurrencyNotSupportedException::new);

        String uri = "https://api.coinbase.com/v2/prices/" + crypto + "-" + currency + "/spot";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.getForEntity(uri, Object.class);
        if (response.getBody() == null)
            throw new ExternalApiResponseInvalidException();
        ObjectMapper objectMapper = new ObjectMapper();
        ConversionInformationDto conversionInformationDto = objectMapper.convertValue(response.getBody(), ConversionInformationDto.class);
        return Double.parseDouble(conversionInformationDto.getData().getAmount());
    }


}
