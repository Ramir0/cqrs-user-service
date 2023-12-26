package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.exception.UserNotFoundException;
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
    void test_handleBadRequestException_ReturnsStatusCode400() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        ResponseEntity<String> response = underTest.handleMethodArgumentNotValidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("There is missing data in the Request", response.getBody());
    }
}
