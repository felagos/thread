package com.app.thread.bank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity(name = "bank_accounts")
@Data
public class BankAccount {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @OneToMany(mappedBy = "bankAccount")
    private List<BankAccountTransaction> transactions;

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accountType='" + accountType + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
