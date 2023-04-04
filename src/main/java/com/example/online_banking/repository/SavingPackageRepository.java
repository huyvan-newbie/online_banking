package com.example.online_banking.repository;

import com.example.online_banking.model.Saving;
import com.example.online_banking.model.SavingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingPackageRepository extends JpaRepository<SavingPackage, Long> {
}
