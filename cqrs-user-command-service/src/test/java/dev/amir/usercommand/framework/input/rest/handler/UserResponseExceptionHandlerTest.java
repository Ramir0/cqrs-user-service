package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.exception.UserPasswordValidationException;
import dev.amir.usercommand.domain.validator.AttributeErrorType;
import dev.amir.usercommand.domain.valueobject.user.UserEmail;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UserResponseExceptionHandlerTest {
    private UserResponseExceptionHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserResponseExceptionHandler();
    }

    @Test
    void test_handleUnknownException_ReturnsStatusCode500() {
        Exception ex = new Exception();

        ResponseEntity<String> response = underTest.handleUnknownException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", response.getBody());
    }

    @Test
    void test_handleUserNotFoundException_ReturnsStatusCode404() {
        UserNotFoundException ex = new UserNotFoundException(UUID.randomUUID());

        ResponseEntity<String> response = underTest.handleUserNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void test_handleMethodArgumentTypeMismatchException_ReturnsStatusCode400() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);

        ResponseEntity<String> response = underTest.handleMethodArgumentTypeMismatchException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Request contains invalid data", response.getBody());
    }

    @Test
    void test_handleMethodArgumentNotValidException_ReturnsStatusCode400() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        ResponseEntity<String> response = underTest.handleMethodArgumentNotValidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("There is missing data in the Request", response.getBody());
    }

    @Test
    void test_handleUserPasswordValidationException_ReturnsStatusCode400() {
        Set<AttributeErrorType> errors = Set.of(
                AttributeErrorType.MAX_LIMIT_EXCEEDED,
                AttributeErrorType.INVALID_VALUE
        );
        UserPasswordValidationException ex = new UserPasswordValidationException(errors);

        ResponseEntity<Set<AttributeErrorType>> response = underTest.handleUserPasswordValidationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, response.getBody());
    }

    @Test
    void test_handleDuplicateUserException_ReturnsStatusCode409() {
        DuplicateUserException ex = new DuplicateUserException(new UserEmail("new_email@test.com"));

        ResponseEntity<String> response = underTest.handleDuplicateUserException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("duplicate data", response.getBody());
    }
}
