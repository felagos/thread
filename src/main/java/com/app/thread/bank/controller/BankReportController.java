package com.app.thread.bank.controller;

import com.app.thread.bank.repository.BankAccountRepository;
import com.app.thread.bank.runnable.ReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController()
@RequestMapping("/bank-report")
public class BankReportController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    @GetMapping()
    public String generateReport() {
        var bankAccounts = this.bankAccountRepository.getAllBankAccounts();

        bankAccounts.forEach(account -> {
            var futureTask = executor.submit(new ReportProcessor(account, this.bankAccountRepository));
            try {
                System.out.println("operation result: " + futureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        });

        return "Done";
    }

}
