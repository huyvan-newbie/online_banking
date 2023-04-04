package com.example.online_banking.rest.model;

import lombok.Data;

@Data
public class LoansInput {
    private String SNN;
    private String amount;
    private Long loansPackageID;
}
