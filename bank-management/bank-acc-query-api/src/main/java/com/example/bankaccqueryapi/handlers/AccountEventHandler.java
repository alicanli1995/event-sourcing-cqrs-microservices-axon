package com.example.bankaccqueryapi.handlers;

import com.example.bankcore.events.AccountClosedEvent;
import com.example.bankcore.events.AccountOpenedEvent;
import com.example.bankcore.events.FundsDepositedEvent;
import com.example.bankcore.events.FundsWithdrawEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawEvent event);
    void on(AccountClosedEvent event);
}
