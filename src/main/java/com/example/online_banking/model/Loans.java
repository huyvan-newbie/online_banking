package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "loans")
@Data
public class Loans {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_package_id")
    private Long loansPackId;

    @Column(name = "status")
    private String status;

    @Column(name = "loans_amount_taken")
    private BigDecimal loansAmountTaken;

    @Column(name = "loans_amount_repaid")
    private BigDecimal loansAmountRepaid;

    @Column(name = "dateOfPayment")
    private Date dateOfPayment;

    @Column(name = "user_id")
    private Long userId;
}
