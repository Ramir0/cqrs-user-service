package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        RoleId roleId,
        @Valid @NotNull Username username,
        @Valid @NotNull Password password,
        @Valid @NotNull FirstName firstName,
        @Valid @NotNull LastName lastname,
        @Valid @NotNull Email email
) {
}
