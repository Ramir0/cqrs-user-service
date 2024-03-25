package dev.amir.usercommand.domain.exception;

import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.Username;

public class DuplicateUserException extends UserException {
    public DuplicateUserException(Email email) {
        super("User with email: " + email + " already exists");
    }

    public DuplicateUserException(Username userName) {
        super("User with username : " + userName + " already exists");
    }
}
