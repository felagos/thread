package com.app.thread.bank.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "bank_account_transactions")
@Data
public class BankAccountTransaction {

    @Id
    @Column(name = "id_transaction")
    private String idTrx;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Date created;

    @Column(name = "transaction_type", nullable = false)
    private String trxType;

    @Transient
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "accountNumber", nullable = false)
    private BankAccount bankAccount;

    @Override
    public String toString() {
        return "BankAccountTransaction{" +
                "idTrx='" + idTrx + '\'' +
                ", amount=" + amount +
                ", created=" + created +
                ", trxType='" + trxType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
