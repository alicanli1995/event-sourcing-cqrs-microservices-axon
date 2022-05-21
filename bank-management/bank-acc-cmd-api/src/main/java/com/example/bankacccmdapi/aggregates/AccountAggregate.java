package com.example.bankacccmdapi.aggregates;

import com.example.bankacccmdapi.commands.CloseAccCommand;
import com.example.bankacccmdapi.commands.DepositFundsCommand;
import com.example.bankacccmdapi.commands.OpenAccCommand;
import com.example.bankacccmdapi.commands.WithdrawFundsCommand;
import com.example.bankcore.events.AccountClosedEvent;
import com.example.bankcore.events.AccountOpenedEvent;
import com.example.bankcore.events.FundsDepositedEvent;
import com.example.bankcore.events.FundsWithdrawEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private String accountHolderId;

    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccCommand openAccCommand){
        var event = AccountOpenedEvent.builder()
                .id(openAccCommand.getId())
                .accountHolderId(openAccCommand.getAccountHolderId())
                .accountType(openAccCommand.getAccountType())
                .createDate(new Date())
                .openingBalance(openAccCommand.getOpeningBalance())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent accountOpenedEvent){
        this.id = accountOpenedEvent.getId();
        this.accountHolderId = accountOpenedEvent.getAccountHolderId();
        this.balance = accountOpenedEvent.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command){
        var amount = command.getAmount();
        var event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance + (amount))
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event){
        this.balance = this.balance+(event.getAmount());
    }


    @CommandHandler
    public void handle(WithdrawFundsCommand command){
        var amount = command.getAmount();
        if((this.balance - (amount)) < 0)
            throw new IllegalStateException("Withdraw declined, balance not enough for this process.");

        var event = FundsWithdrawEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance-(amount))
                .build();
        AggregateLifecycle.apply(event);
    }


    @EventSourcingHandler
    public void on(FundsWithdrawEvent event){
        this.balance = this.balance -(event.getAmount());
    }

    @CommandHandler
    public void handle(CloseAccCommand command){
        var event = AccountClosedEvent.builder()
                .id(command.getId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event){
        AggregateLifecycle.markDeleted();
    }


}
