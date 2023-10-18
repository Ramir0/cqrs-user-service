package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.domain.exception.UserNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseExceptionHandlerTest {
    private UserResponseExceptionHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserResponseExceptionHandler();
    }

    @Test
<<<<<<< HEAD
<<<<<<< HEAD
    public void test_HandleUnknownException() {
=======
    public void test_HandleUnknownException_ForGetAllUsers() {
>>>>>>> main
=======
    public void test_HandleUnknownException() {
>>>>>>> f40267853d105cfcda1bf6ea3a67d968939e479e
        Exception ex = new Exception();
        ResponseEntity<String> response = underTest.handleUnknownException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", response.getBody());
    }

    @Test
<<<<<<< HEAD
<<<<<<< HEAD
    public void test_HandleUserNotFoundException() {
=======
    public void test_HandleUserNotFoundException_ForGetUsersById() {
>>>>>>> main
=======
    public void test_HandleUserNotFoundException() {
>>>>>>> f40267853d105cfcda1bf6ea3a67d968939e479e
        String expectedUuid = UUID.randomUUID().toString();
        UserNotFoundException ex = new UserNotFoundException(expectedUuid);
        ResponseEntity<String> response = underTest.handleUserNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}
