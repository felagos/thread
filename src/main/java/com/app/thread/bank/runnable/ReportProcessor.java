package com.app.thread.bank.runnable;

import com.app.thread.bank.entities.BankAccount;
import com.app.thread.bank.repository.BankAccountRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

@Component
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

        try (var writter = new BufferedWriter((new FileWriter((file))))) {
            transactions.forEach(trx -> {
                try {
                    var sb = new StringBuilder();
                    sb.append("Account Number ").append(trx.getAccountNumber());
                    sb.append("Transaction Typ ").append(trx.getTrxType());
                    sb.append("Transaction Id ").append(trx.getIdTrx());
                    sb.append("Transaction Amount ").append(trx.getAmount());
                    sb.append("Transaction Date ").append(trx.getCreated());

                    writter.write(sb.toString());
                    writter.newLine();

                    writter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            reportedGenerated = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return reportedGenerated;
    }
}
