package com.mpepke.cryptocurrencyconverter.service;

import com.mpepke.cryptocurrencyconverter.dto.ConversionInformationDto;
import com.mpepke.cryptocurrencyconverter.dto.CryptocurrencyData;
import com.mpepke.cryptocurrencyconverter.service.exception.CryptocurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.exception.CurrencyNotSupportedException;
import com.mpepke.cryptocurrencyconverter.service.impl.ConversionInformationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ConversionInformationServiceTest {


    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private ConversionInformationServiceImpl service;
    private final String cryptoTest = "BTC";
    private final String currencyTest = "USD";

    @Test(expected = CryptocurrencyNotSupportedException.class)
    public void getCurrentCryptocurrencyPrice_whenCryptocurrencyNotSupported_throwException() throws Exception {
        String crypto = "XYZ";
        String currency = "USD";
        service.getSpotPrice(crypto, currency);

    }

    @Test(expected = CurrencyNotSupportedException.class)
    public void getCurrentCryptocurrencyPrice_whenCurrencyNotSupported_throwException() throws Exception {
        String crypto = "BTC";
        String currency = "PLN";
        service.getSpotPrice(crypto, currency);

    }


    @Test
    public void getCurrentCryptocurrencyPrice_getSpotPrice() throws Exception {

        double price = service.getSpotPrice(cryptoTest, currencyTest);
        ConversionInformationDto conversionInformationDto = new ConversionInformationDto(new CryptocurrencyData("BTC", "ETH", String.valueOf(price)));

        when(restTemplate.getForEntity("https://api.coinbase.com/v2/prices/" + cryptoTest + "-" + currencyTest + "spot", ConversionInformationDto.class))
                .thenReturn(new ResponseEntity<>(conversionInformationDto, HttpStatus.OK));
        Assertions.assertThat(Double.parseDouble(conversionInformationDto.getData().getAmount())).isEqualTo(price);
    }

}
