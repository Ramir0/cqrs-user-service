package dev.amir.usercommand.domain.exception;

public class UserValidationException extends UserException {
    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
