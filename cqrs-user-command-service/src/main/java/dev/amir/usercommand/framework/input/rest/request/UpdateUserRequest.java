package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.UserEmail;
import dev.amir.usercommand.domain.valueobject.user.UserGender;
import dev.amir.usercommand.domain.valueobject.user.UserLastName;
import dev.amir.usercommand.domain.valueobject.user.UserName;
import dev.amir.usercommand.domain.valueobject.user.UserStatus;
import dev.amir.usercommand.domain.valueobject.user.UserUsername;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        RoleId roleId,
        @Valid @NotNull UserUsername username,
        @Valid @NotNull UserName name,
        @Valid @NotNull UserLastName lastname,
        @Valid @NotNull UserEmail email,
        UserStatus status,
        UserGender gender
) {
}
