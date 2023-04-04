package com.example.online_banking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionID;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "transaction_amount", nullable = false)
    @Min(value = 0)
    private BigDecimal transactionAmount;

    @Column(name = "transaction_date", updatable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "recipient_account_id")
    private Long recipientAccountID;

    @Column(name = "transaction_type")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
