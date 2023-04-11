package com.mpepke.cryptocurrencyconverter.controller.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConversionRequest {

    private String cryptocurrency;
    private String currency;
    private double amount;
}
