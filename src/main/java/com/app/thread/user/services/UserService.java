package com.app.thread.user.services;

import com.app.thread.user.entities.User;
import com.app.thread.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

}
