package com.example.bankaccqueryapi.dto;


import com.example.bankcore.dto.BaseResponse;
import com.example.bankcore.models.BankAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {

    @Getter
    @Setter
    private List<BankAccount> accountList;

    public AccountLookupResponse(String message) {
        super(message);
    }


    public AccountLookupResponse(List<BankAccount> accountList, String message) {
        super(message);
        this.accountList = accountList;
    }

    public AccountLookupResponse(BankAccount account, String message) {
        super(message);
        this.accountList = new ArrayList<>();
        this.accountList.add(account);
    }

}
