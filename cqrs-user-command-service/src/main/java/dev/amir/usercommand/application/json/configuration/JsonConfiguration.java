package dev.amir.usercommand.application.json.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.amir.usercommand.application.json.UserPasswordDeserializer;
import dev.amir.usercommand.domain.validator.PasswordValidator;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JsonConfiguration {
    @Bean
    public ObjectMapper objectMapper(PasswordEncoder passwordEncoder, PasswordValidator passwordValidator) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(UserPassword.class, new UserPasswordDeserializer(passwordEncoder, passwordValidator));

        return JsonMapper.builder()
                .constructorDetector(ConstructorDetector.DEFAULT)
                .addModule(module)
                .addModule(new JavaTimeModule())
                .build();
    }
}
