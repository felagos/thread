package com.app.thread.bank.repository;

import com.app.thread.bank.entities.BankAccount;
import com.app.thread.bank.entities.BankAccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankAccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BankAccount> getAllBankAccounts() {
        return this.jdbcTemplate.query("SELECT * FROM bank_accounts", (rs) -> {
            var bankAccounts = new ArrayList<BankAccount>();

            while (rs.next()) {
                var bankAccount = new BankAccount();
                bankAccount.setAccountNumber(rs.getString("account_number"));
                bankAccount.setName(rs.getString("name"));
                bankAccount.setEmail(rs.getString("email"));
                bankAccount.setAccountType(rs.getString("account_type"));

                bankAccounts.add(bankAccount);
            }

            return bankAccounts;
        });

    }

    public List<BankAccountTransaction> getTransactionByAccount(String accountNumber) {
        PreparedStatementSetter parameters = ps -> ps.setString(1, accountNumber);

        return this.jdbcTemplate.query(
                "SELECT * FROM bank_account_transactions WHERE account_number = ?",
                parameters,
                (rs, rowNum) -> {
                    var transaction = new BankAccountTransaction();
                    transaction.setIdTrx(rs.getString("id_transaction"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setCreated(rs.getDate("created"));
                    transaction.setTrxType(rs.getString("transaction_type"));
                    transaction.setAccountNumber(accountNumber);

                    return transaction;
                });

    }

}
