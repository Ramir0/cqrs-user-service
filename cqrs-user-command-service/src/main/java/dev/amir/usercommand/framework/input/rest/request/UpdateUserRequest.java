package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UpdateUserRequest(
        RoleId roleId,
        @Valid @NotNull UserUsername username,
        @Valid @NotNull UserName name,
        @Valid @NotNull UserLastName lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
