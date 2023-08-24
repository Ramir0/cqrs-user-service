package dev.amir.userquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class UserQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryServiceApplication.class, args);
        log.info("Running User Query Service.");
        }
    }
