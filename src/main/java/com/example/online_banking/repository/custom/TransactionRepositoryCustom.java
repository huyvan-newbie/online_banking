package com.example.online_banking.repository.custom;

import com.example.online_banking.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class TransactionRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    public void insertLog(Transaction transaction) {
        try {
            entityManager.persist(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLog(Transaction transaction) {
        try {
            entityManager.merge(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
