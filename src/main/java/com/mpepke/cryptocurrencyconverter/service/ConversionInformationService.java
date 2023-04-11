package com.mpepke.cryptocurrencyconverter.service;


import com.mpepke.cryptocurrencyconverter.service.exception.CurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.CryptocurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.ExternalApiResponseInvalidException;

public interface ConversionInformationService {

    /**
     *
     * Gets the spot price of a given cryptocurrency
     * @param  crypto
     * cryptocurrency name
     * @return
     * the current price of the cryptocurrency
     */
    double getSpotPrice(final String crypto, final String currency) throws CryptocurrencyNotSupportedException, CurrencyNotSupportedException, ExternalApiResponseInvalidException;
}
