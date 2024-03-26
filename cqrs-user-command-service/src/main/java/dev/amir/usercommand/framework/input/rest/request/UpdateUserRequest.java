package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.Gender;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.domain.valueobject.user.Status;
import dev.amir.usercommand.domain.valueobject.user.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        RoleId roleId,
        @Valid @NotNull Username username,
        @Valid @NotNull FirstName firstname,
        @Valid @NotNull LastName lastname,
        @Valid @NotNull Email email,
        Status status,
        Gender gender
) {
}
