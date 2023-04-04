package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "saving")
@Data
public class Saving {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saving_package_id")
    private Long savingPackageId;

    @Column(name = "status")
    private String status;

    @Column(name = "saving_amount_taken")
    private BigDecimal savingAmountTaken;

    @Column(name = "saving_amount_profit")
    private BigDecimal savingAmounProfit;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "user_id")
    private Long userId;
}
