package dev.amir.usercommand.application.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import dev.amir.usercommand.domain.exception.UserPasswordValidationException;
import dev.amir.usercommand.domain.validator.AttributeErrorType;
import dev.amir.usercommand.domain.validator.AttributeValidator;
import dev.amir.usercommand.domain.valueobject.user.UserPassword;
import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

@Slf4j
@RequiredArgsConstructor
public class UserPasswordDeserializer extends JsonDeserializer<UserPassword> {
    private final PasswordEncoder passwordEncoder;
    private final AttributeValidator<String> passwordValidator;

    @Override
    public UserPassword deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        log.debug("Deserialize and Encrypt password field");
        String userPassword = parser.getValueAsString();
        Set<AttributeErrorType> errors = passwordValidator.validate(userPassword);
        if (!CollectionUtils.isEmpty(errors)) {
            throw new UserPasswordValidationException(errors);
        }

        String encodedPassword = passwordEncoder.encode(userPassword);
        return new UserPassword(encodedPassword);
    }
}
