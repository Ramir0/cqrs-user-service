package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.user.BirthDate;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.Gender;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateProfileRequest(
        @Valid @NotNull FirstName firstName,
        @Valid @NotNull LastName lastname,
        Gender gender,
        @Valid BirthDate birthDate) {
}
