package com.mpepke.cryptocurrencyconverter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "history")
@Builder(toBuilder = true)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "cryptocurrency")
    private CryptocurrencyEnum cryptocurrency;

    @Column(name = "cryptocurrency_amount")
    private double cryptocurrencyAmount;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @Column(name = "amount_per")
    private double amount;

    @Column(name = "total_amount")
    private double totalAmount;


}
