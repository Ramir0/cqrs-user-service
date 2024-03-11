package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.domain.exception.UserValidationException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserIdTest {
    @Test
    void test_NoArgsConstructor() {
        UserId userId = new UserId();

        assertNotNull(userId.getValue());
    }

    @Test
    void test_StringArgConstructor() {
        String expected = UUID.randomUUID().toString();
        UserId userId = new UserId(expected);

        assertEquals(expected, userId.toString());
    }

    @Test
    void test_UuidArgConstructor() {
        UUID expected = UUID.randomUUID();
        UserId userId = new UserId(expected);

        assertEquals(expected, userId.getValue());
    }

    @Test
    void test_StringArgConstructor_WhenInvalidValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new UserId("123")
        );
    }

    @Test
    void test_UuidArgConstructor_WhenNullValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new UserId((UUID) null)
        );
    }
}
