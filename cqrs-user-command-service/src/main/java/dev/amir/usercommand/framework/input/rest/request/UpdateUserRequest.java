package dev.amir.usercommand.framework.input.rest.request;

import dev.amir.usercommand.domain.valueobject.UserStatus;

public record UpdateUserRequest(String name, String lastname, String email, UserStatus status) {
}
