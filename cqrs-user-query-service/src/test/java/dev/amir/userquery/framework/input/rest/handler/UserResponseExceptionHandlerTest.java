package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.domain.exception.UserNotFoundException;
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
    public void test_HandleUnknownExceptionForGetAllUsers() {
        Exception ex = new Exception();
        ResponseEntity<String> response = handler.handleUnknownException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", response.getBody());
    }

    @Test
    public void test_HandleUserNotFoundExceptionForGetUsersById() {
        String expectedUuid = UUID.randomUUID().toString();
        UserNotFoundException ex = new UserNotFoundException(expectedUuid);
        ResponseEntity<String> response = handler.handleUserNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}
