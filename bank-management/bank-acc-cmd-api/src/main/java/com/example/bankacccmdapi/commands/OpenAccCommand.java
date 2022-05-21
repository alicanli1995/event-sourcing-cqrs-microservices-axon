package com.example.bankacccmdapi.commands;

import com.example.bankcore.models.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccCommand {


    @TargetAggregateIdentifier
    private String id;

    @NotBlank(message = "Account holder id does not be blank !")
    private String accountHolderId;

    @NotNull(message = "AccountType is required.")
    private AccountType accountType;

    @NotNull(message = "Balance is required.")
    @Min(value = 50, message = "Must be higher than 50 .")
    private double openingBalance;

}
