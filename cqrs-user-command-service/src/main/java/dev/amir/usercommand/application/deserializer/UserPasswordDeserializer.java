package dev.amir.usercommand.application.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class UserPasswordDeserializer extends JsonDeserializer<UserPassword> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserPassword deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        log.debug("Deserialize and Encrypt password field");
        final String password = passwordEncoder.encode(parser.getValueAsString());
        return new UserPassword(password);
    }
}
