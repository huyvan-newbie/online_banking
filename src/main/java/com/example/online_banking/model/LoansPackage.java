package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LoansPackage")
@Data
public class LoansPackage {
    @Id
    @Column(name = "loan_package_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "duration")
    private Integer duration;

}
