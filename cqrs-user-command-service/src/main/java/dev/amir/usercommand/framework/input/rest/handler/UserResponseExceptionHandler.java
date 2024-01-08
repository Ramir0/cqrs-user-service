package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.exception.UserPasswordValidationException;
import dev.amir.usercommand.domain.validator.AttributeErrorType;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        final String message = "Request contains invalid data";
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final String message = "There is missing data in the Request";
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(UserPasswordValidationException.class)
    public ResponseEntity<Set<AttributeErrorType>> handleUserPasswordValidationException(
            UserPasswordValidationException ex
    ) {
        String errorsAsString = ex.getErrors()
                .stream()
                .map(AttributeErrorType::name)
                .collect(Collectors.joining(", "));
        log.error("Errors in password: [{}]", errorsAsString, ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getErrors());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException ex) {
        log.error("Data already exists", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("duplicate data");
    }
}
