package dev.amir.usercommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class UserCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCommandServiceApplication.class, args);
    }
}
