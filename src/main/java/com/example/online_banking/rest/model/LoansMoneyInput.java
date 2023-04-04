package com.example.online_banking.rest.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoansMoneyInput {
    private Long loansPackageID;
    private BigDecimal amount;
}
