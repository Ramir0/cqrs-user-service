package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class UserResponseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnknownException(Exception ex) {
        log.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal error");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("The user was not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleBadRequestException(MethodArgumentTypeMismatchException ex) {
        log.error("There is a bad request ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad request");
    }
}
