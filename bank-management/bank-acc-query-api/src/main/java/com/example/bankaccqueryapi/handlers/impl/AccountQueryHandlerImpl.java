package com.example.bankaccqueryapi.handlers.impl;

import com.example.bankaccqueryapi.dto.AccountLookupResponse;
import com.example.bankaccqueryapi.dto.EqualityType;
import com.example.bankaccqueryapi.handlers.AccountQueryHandler;
import com.example.bankaccqueryapi.queries.FindAccountByHolderIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountByIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountsWithBalanceQuery;
import com.example.bankaccqueryapi.queries.FindAllAccountsQuery;
import com.example.bankaccqueryapi.repository.AccountRepository;
import com.example.bankcore.models.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
@RequiredArgsConstructor
public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;


    @Override
    @QueryHandler
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var acc = accountRepository.findById(query.getId());
        return acc.map(bankAccount -> new AccountLookupResponse(bankAccount, "Acc Successfully Returned."))
                .orElseGet(() -> new AccountLookupResponse("No bank account in database , id -> " + query.getId()));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
        var acc = accountRepository.findByAccountHolderId(query.getAccountByHolderId());
        return acc.map(bankAccount -> new AccountLookupResponse(bankAccount, "Acc Successfully Returned."))
                .orElseGet(() -> new AccountLookupResponse("No bank account in database , holder id -> " + query.getAccountByHolderId()));
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var accList = accountRepository.findAll();
        if(!accList.iterator().hasNext())
            return new AccountLookupResponse("No Bank Acc were found ... !");
        var accs = new ArrayList<BankAccount>();
        accList.forEach(accs::add);
        var count = accs.size();
        return new AccountLookupResponse(accs, "All records successfully returned. " + count + " records returned.");
    }

    @Override
    @QueryHandler
    public AccountLookupResponse findAccountsByBalance(FindAccountsWithBalanceQuery query) {
        var bankAccounts = query.getEqualityType().equals(EqualityType.GREATER_THAN) ?
                accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());
        return bankAccounts  != null && !bankAccounts.isEmpty() ?
                    new AccountLookupResponse(bankAccounts, "Success return records ")
                    : new AccountLookupResponse("Database has not record for this query...!");
    }
}
