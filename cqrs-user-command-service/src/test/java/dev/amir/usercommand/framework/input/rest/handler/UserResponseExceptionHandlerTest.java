package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.UserNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseExceptionHandlerTest {
    private UserResponseExceptionHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new UserResponseExceptionHandler();
    }


    @Test
    void test_handleUnknownException() {
        Exception ex = new Exception();
        ResponseEntity<String> response = handler.handleUnknownException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", response.getBody());
    }

    @Test
    void test_handleUserNotFoundException() {
        UserNotFoundException ex = new UserNotFoundException(UUID.randomUUID());
        ResponseEntity<String> response = handler.handleUserNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}