package com.app.thread.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;




}
