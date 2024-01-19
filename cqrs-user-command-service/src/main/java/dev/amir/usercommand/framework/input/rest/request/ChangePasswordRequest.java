package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull @Valid UserPassword password
) {
}
