package com.example.bankcore.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundsWithdrawEvent {

    private String id;

    private BigDecimal amount;

    private BigDecimal balance;

}
