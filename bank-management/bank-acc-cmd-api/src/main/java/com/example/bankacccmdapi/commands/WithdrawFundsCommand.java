package com.example.bankacccmdapi.commands;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawFundsCommand {

    @TargetAggregateIdentifier
    private String id;

    private BigDecimal amount;

    private BigDecimal balance;
}
