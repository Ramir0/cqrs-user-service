package dev.amir.userquery.domain.exception;

import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserNotFoundExceptionTest {

    @Test
    void test_StringArgConstructor() {
        String userId = UUID.randomUUID().toString();

        UserNotFoundException underTest = new UserNotFoundException(userId);

        assertEquals("User with ID:" + userId + " Not found", underTest.getMessage());
        assertNull(underTest.getCause());
    }
}
