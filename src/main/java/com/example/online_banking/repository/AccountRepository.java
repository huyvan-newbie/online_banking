package com.example.online_banking.repository;

import com.example.online_banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByAccountNumber(String accNum);
    Account findFirstByUserId(Long userId);
    Account findByAccountNumberAndBankId(String accountNumber, Long bankId);
}
