package com.bayztracker.api.commandLineRunners;

import com.bayztracker.api.entities.User;
import com.bayztracker.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class usersCLR implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(usersCLR.class);
    private final UserRepository userRepository;

    public usersCLR(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        userRepository.save(new User("user", "userpass", User.UserType.USER));
        userRepository.save(new User("admin", "adminpass", User.UserType.ADMIN));

        for (User user : userRepository.findAll()) {
            logger.info("{}", user);
        }
    }
}
