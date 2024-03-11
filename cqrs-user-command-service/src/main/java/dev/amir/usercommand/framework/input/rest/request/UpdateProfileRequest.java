package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.user.UserBirthDate;
import dev.amir.usercommand.domain.valueobject.user.UserGender;
import dev.amir.usercommand.domain.valueobject.user.UserLastName;
import dev.amir.usercommand.domain.valueobject.user.UserName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateProfileRequest(
        @Valid @NotNull UserName name,
        @Valid @NotNull UserLastName lastname,
        UserGender gender,
        @Valid UserBirthDate birthDate) {
}
