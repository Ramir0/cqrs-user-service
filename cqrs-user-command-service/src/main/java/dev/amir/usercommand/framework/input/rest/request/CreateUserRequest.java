package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        RoleId roleId,
        @Valid @NotNull UserUsername username,
        @Valid @NotNull UserPassword password,
        @Valid @NotNull UserName name,
        @Valid @NotNull UserLastName lastname,
        @Valid @NotNull UserEmail email
) {
}
