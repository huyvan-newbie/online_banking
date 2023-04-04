package com.example.online_banking.repository;

import com.example.online_banking.model.Loans;
import com.example.online_banking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    List<Loans> findUserByUserId(Long userId);
}
