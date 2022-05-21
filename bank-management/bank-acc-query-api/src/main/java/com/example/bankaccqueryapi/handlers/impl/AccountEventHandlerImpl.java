package com.example.bankaccqueryapi.handlers.impl;

import com.example.bankaccqueryapi.handlers.AccountEventHandler;
import com.example.bankaccqueryapi.repository.AccountRepository;
import com.example.bankcore.events.AccountClosedEvent;
import com.example.bankcore.events.AccountOpenedEvent;
import com.example.bankcore.events.FundsDepositedEvent;
import com.example.bankcore.events.FundsWithdrawEvent;
import com.example.bankcore.models.BankAccount;
import lombok.extern.log4j.Log4j2;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @EventHandler
    public void on(AccountOpenedEvent event) {
        var bankAcc = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .accountType(event.getAccountType())
                .createDate(event.getCreateDate())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAcc);
    }

    @Override
    @EventHandler
    public void on(FundsDepositedEvent event) {

        var bankAcc = accountRepository.findById(event.getId());
        if (bankAcc.isEmpty()) return;

        bankAcc.get().setBalance(event.getBalance());

        accountRepository.save(bankAcc.get());
    }

    @Override
    @EventHandler
    public void on(FundsWithdrawEvent event) {

        var bankAcc = accountRepository.findById(event.getId());
        if (bankAcc.isEmpty()) return;

        bankAcc.get().setBalance(event.getBalance());

        accountRepository.save(bankAcc.get());
    }

    @Override
    @EventHandler
    public void on(AccountClosedEvent event) {

        accountRepository.deleteById(event.getId());

    }
}
