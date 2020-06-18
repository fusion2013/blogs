package com.fusion.mongotemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import com.fusion.mongotemplate.entity.User;
import com.fusion.mongotemplate.repository.UserRepository;

@SpringBootApplication
public class MongoTemplateApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MongoTemplateApplication.class);

    private final UserRepository userRepository;

    public MongoTemplateApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoTemplateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("steve.jobs", "Steve", "Jobs", "steve@gmail.com"));
        userRepository.save(new User("bill.gates", "Bill", "Gates", "bill@microsoft.com"));
        userRepository.save(new User("richard.nixon", "Richard", "Nixon", "richard@gmail.com"));
        userRepository.save(new User("thomas.hobbes", "Thomas", "Hobbes", "thomas@gmail.com"));

        userRepository.findUsers(PageRequest.of(0, 2));
    }
}
