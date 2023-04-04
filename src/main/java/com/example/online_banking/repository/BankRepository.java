package com.example.online_banking.repository;

import com.example.online_banking.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByBankCode(String bankCode);
}
