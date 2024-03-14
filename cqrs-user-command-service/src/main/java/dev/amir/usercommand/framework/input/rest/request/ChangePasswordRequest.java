package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.user.Password;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull @Valid Password password
) {
}
