package com.example.bankacccmdapi.commands;

import com.example.bankcore.models.AccountType;
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
public class OpenAccCommand {


    @TargetAggregateIdentifier
    private String id;

    private String accountHolderId;

    private AccountType accountType;

    private BigDecimal openingBalance;

}
