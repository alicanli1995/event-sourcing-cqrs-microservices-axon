package com.example.bankacccmdapi.commands;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawFundsCommand {

    @TargetAggregateIdentifier
    private String id;

    @Positive(message = "Must be a positive.")
    private double amount;

}
