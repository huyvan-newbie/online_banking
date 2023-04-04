package com.example.online_banking.repository;


import com.example.online_banking.model.LoansPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansPackageRepository extends JpaRepository<LoansPackage,Long> {
}
