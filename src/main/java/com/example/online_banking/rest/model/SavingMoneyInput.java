package com.example.online_banking.rest.model;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavingMoneyInput {
    private Long savingPackageID;
    private BigDecimal amount;
}
