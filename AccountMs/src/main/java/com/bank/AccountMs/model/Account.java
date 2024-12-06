package com.bank.AccountMs.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Long customerId;

    public Account(Long id, String accountNumber, double balance, AccountType accountType, Long customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = customerId;
    }

    public Account() {
    }
}

