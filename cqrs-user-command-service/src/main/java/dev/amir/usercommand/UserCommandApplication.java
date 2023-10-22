package dev.amir.usercommand;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@NoArgsConstructor
@SpringBootApplication
public class UserCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }
}
