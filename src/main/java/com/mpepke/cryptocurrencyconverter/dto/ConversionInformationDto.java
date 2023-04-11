package com.mpepke.cryptocurrencyconverter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ConversionInformationDto {

    private CryptocurrencyData data;

}
