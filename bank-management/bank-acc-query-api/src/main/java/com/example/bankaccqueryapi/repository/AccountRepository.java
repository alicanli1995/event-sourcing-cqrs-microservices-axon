package com.example.bankaccqueryapi.repository;

import com.example.bankcore.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount,String> {
}
