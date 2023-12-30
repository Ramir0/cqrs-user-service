package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateUserRequest(
        UUID roleId,
        @Valid
        @NotNull
        UserUsername username,
        String name,
        String lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
