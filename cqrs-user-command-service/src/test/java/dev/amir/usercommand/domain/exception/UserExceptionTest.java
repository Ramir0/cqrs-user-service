package dev.amir.usercommand.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserExceptionTest {
    @Test
    void test_StringArgConstructor() {
        String expectedMessage = "Test message";

        UserException underTest = new UserException(expectedMessage);

        assertEquals(expectedMessage, underTest.getMessage());
        assertNull(underTest.getCause());
    }

    @Test
    void  test_StringAndThrowableArgConstructor() {
        String expectedMessage = "Test message with cause";
        Throwable expectedCause = new NullPointerException("A field is null");

        UserException underTest = new UserException(expectedMessage, expectedCause);

        assertEquals(expectedMessage, underTest.getMessage());
        assertInstanceOf(NullPointerException.class, underTest.getCause());
        assertEquals(expectedCause, underTest.getCause());
    }
}
