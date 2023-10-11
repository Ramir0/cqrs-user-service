package dev.amir.userquery.domain.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String userId) {
        super("User with id:" + userId + " Not found");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
