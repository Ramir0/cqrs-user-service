package dev.amir.usercommand.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(UUID userId) {
        super("User with id: " + userId + " Not found", null);
    }
}
