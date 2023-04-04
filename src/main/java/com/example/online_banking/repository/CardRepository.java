package com.example.online_banking.repository;

import com.example.online_banking.model.Account;
import com.example.online_banking.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String accNum);
    Card findByUserId(Long userId);
}
