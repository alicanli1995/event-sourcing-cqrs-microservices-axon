package com.example.bankaccqueryapi.repository;

import com.example.bankcore.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount,String> {

    Optional<BankAccount> findByAccountHolderId (String s);

    List<BankAccount> findByBalanceGreaterThan(double balance);
    List<BankAccount> findByBalanceLessThan(double balance);

}
