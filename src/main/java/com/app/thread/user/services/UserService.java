package com.app.thread.user.services;

import com.app.thread.user.entities.User;
import com.app.thread.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer saveUser(User user) {
        return this.userRepository.saveUser(user);
    }

}
