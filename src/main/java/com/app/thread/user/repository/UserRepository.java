package com.app.thread.user.repository;

import com.app.thread.user.entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void cleanUsers() {
        jdbcTemplate.update("delete from users");
    }

    public Integer saveUser(User user) {
        String sql = """
                INSERT into users(id, email_address, name)
                VALUES (?, ?, ?);
                """;

        return jdbcTemplate.update(sql, user.getId(),
                user.getEmailAddress(),
                user.getName());
    }
}
