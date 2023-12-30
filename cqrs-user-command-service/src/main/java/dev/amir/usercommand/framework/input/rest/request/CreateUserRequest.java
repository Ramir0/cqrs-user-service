package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record CreateUserRequest(
        UUID roleId,
        @Valid
        @NotNull
        UserUsername username,
        @Valid
        @NotNull
        UserPassword password,
        String name,
        String lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
