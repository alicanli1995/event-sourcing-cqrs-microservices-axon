package com.example.bankaccqueryapi.handlers;

import com.example.bankaccqueryapi.dto.AccountLookupResponse;
import com.example.bankaccqueryapi.queries.FindAccountByHolderIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountByIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountsWithBalanceQuery;
import com.example.bankaccqueryapi.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {

    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountsByBalance(FindAccountsWithBalanceQuery query);

}
