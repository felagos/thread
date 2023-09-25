package com.app.thread.runnables;

import com.app.thread.user.entities.User;
import com.app.thread.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;


public class UserProcessor implements Callable<Integer> {

    private final String userRecord;
    private final UserService userService;

    public UserProcessor(String userRecord, UserService userService) {
        this.userRecord = userRecord;
        this.userService = userService;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " processing record for " + userRecord);

        Integer totalSaved = 0;
        var tokenizer = new StringTokenizer(userRecord,",");

        while (tokenizer.hasMoreTokens()) {
            var user = new User();

            user.setEmailAddress(tokenizer.nextToken());
            user.setName(tokenizer.nextToken());
            user.setId(Integer.valueOf(tokenizer.nextToken()));

            totalSaved += this.userService.saveUser(user);
        }

        System.out.println("totalSaved " + totalSaved);

        return totalSaved;
    }
}
