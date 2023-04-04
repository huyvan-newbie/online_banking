package com.example.online_banking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SavingPackage")
@Data
public class SavingPackage {
        @Id
        @Column(name = "saving_package_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "interest_rate")
        private double interestRate;

        @Column(name = "duration")
        private Integer duration;

}
