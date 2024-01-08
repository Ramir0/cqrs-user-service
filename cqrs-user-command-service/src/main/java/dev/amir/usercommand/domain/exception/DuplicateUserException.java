package dev.amir.usercommand.domain.exception;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserUsername;

public class DuplicateUserException extends UserException {
    public DuplicateUserException(UserEmail email) {
        super("User with email: " + email + " already exists");
    }

    public DuplicateUserException(UserUsername userName) {
        super("User with username : " + userName + " already exists");
    }
}
