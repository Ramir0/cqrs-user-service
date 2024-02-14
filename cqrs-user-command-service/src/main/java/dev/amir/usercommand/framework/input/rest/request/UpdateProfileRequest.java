package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserBirthDate;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateProfileRequest(
        @Valid @NotNull UserName name,
        @Valid @NotNull UserLastName lastname,
        UserGender gender,
        @Valid UserBirthDate birthDate) {
}
