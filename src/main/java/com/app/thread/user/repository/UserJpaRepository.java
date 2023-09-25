package com.app.thread.user.repository;

import com.app.thread.user.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Integer> {
}
