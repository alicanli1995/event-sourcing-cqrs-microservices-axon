package com.example.bankaccqueryapi.queries;

import com.example.bankaccqueryapi.dto.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsWithBalanceQuery {
    private EqualityType equalityType;
    private double balance;

}
