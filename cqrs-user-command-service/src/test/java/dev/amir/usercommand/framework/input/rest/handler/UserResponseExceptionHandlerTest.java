package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.UserNotFoundException;
import java.lang.reflect.Parameter;
import java.util.UUID;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void test_handleBadRequestException_ReturnsStatusCode400() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(
                "userId",
                Long.class,
                "abc",
                null,
                null
        );
        ResponseEntity<String> response = underTest.handleBadRequestException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", response.getBody());
    }
}
