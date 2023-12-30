package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import java.util.UUID;

public record UpdateUserRequest(
        UUID roleId,
        UserUsername username,
        String name,
        String lastname,
        String email,
        UserStatus status,
        UserGender gender
) {
}
