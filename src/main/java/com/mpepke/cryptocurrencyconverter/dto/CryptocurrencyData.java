package com.mpepke.cryptocurrencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyData {
    private String base;
    private String currency;
    private String amount;
}
