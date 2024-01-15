package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserPassword;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull @Valid UserPassword password
) {
}
