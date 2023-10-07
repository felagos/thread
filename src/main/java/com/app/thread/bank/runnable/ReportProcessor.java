package com.app.thread.bank.runnable;

import com.app.thread.bank.entities.BankAccount;
import com.app.thread.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ReportProcessor implements Callable<Boolean> {

    private final BankAccount bankAccount;
    private final BankAccountRepository bankAccountRepository;

    public ReportProcessor(BankAccount bankAccount, BankAccountRepository bankAccountRepository) {
        this.bankAccount = bankAccount;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Boolean call() throws Exception {
        boolean reportedGenerated = false;
        var file = new File("./reports/" + bankAccount.getAccountNumber() + "_tx.txt");

        var transactions = this.bankAccountRepository.getTransactionByAccount(bankAccount.getAccountNumber());

        try (var writer = new BufferedWriter((new FileWriter((file))))) {
            transactions.forEach(trx -> {
                try {
                    String sb = "Account Number " + trx.getAccountNumber() +
                            "Transaction Typ " + trx.getTrxType() +
                            "Transaction Id " + trx.getIdTrx() +
                            "Transaction Amount " + trx.getAmount() +
                            "Transaction Date " + trx.getCreated();

                    writer.write(sb);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            writer.flush();

            reportedGenerated = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return reportedGenerated;
    }
}
