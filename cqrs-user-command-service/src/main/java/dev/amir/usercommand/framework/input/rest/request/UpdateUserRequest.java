package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UpdateUserRequest(
        UUID roleId,
        @Valid
        @NotNull
        UserUsername username,
        @Valid
        @NotNull
        UserName name,
        @Valid
        @NotNull
        UserLastName lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
