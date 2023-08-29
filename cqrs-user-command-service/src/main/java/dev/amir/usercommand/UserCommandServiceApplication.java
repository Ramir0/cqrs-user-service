package dev.amir.usercommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UserCommandServiceApplication {

    public static void main(String[] args) {
        log.info("Starting User Command Service application.");
        SpringApplication.run(UserCommandServiceApplication.class, args);
    }
}
