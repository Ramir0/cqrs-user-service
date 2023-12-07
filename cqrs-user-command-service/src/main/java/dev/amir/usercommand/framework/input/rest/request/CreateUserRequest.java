package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import java.util.UUID;

public record CreateUserRequest(UUID roleId, String name, String lastname, String email, UserStatus status,
                                String username, UserGender gender) {
}
