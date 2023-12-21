package dev.amir.usercommand.framework.input.rest.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.amir.usercommand.application.deserializer.UserPasswordDeserializer;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import java.util.UUID;

public record CreateUserRequest(
        UUID roleId,
        String username,
        @JsonDeserialize(using = UserPasswordDeserializer.class)
        UserPassword password,
        String name,
        String lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
