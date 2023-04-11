package com.mpepke.cryptocurrencyconverter.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class HistoryDto {

    private Long id;
    private String cryptocurrency;
    private double cryptocurrencyAmount;
    private String currency;
    private double amountPer;
    private double amountTotal;
}
