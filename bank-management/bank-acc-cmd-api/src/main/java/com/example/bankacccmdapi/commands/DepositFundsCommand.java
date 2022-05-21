package com.example.bankacccmdapi.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositFundsCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull
    @Positive(message = "Must be positive.")
    private double amount;

}
