package com.example.bankcore.events;

import com.example.bankcore.models.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountOpenedEvent {

    private String id;

    private String accountHolderId;

    private AccountType accountType;

    private Date createDate;

    private double openingBalance;

}
