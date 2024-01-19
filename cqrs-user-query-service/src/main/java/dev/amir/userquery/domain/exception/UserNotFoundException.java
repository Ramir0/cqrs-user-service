package dev.amir.userquery.domain.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String userId) {
        super("User with ID:" + userId + " Not found");
    }
}
