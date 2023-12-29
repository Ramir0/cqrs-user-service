package dev.amir.usercommand.application.json.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(UserPassword.class, new UserPasswordDeserializer(passwordEncoder, passwordValidator));
        mapper.registerModule(module);

        return mapper;
    }
}
