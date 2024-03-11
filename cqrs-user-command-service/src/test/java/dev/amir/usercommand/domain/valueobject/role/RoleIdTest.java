package dev.amir.usercommand.domain.valueobject.role;

import dev.amir.usercommand.domain.exception.UserValidationException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleIdTest {
    @Test
    void test_NoArgsConstructor() {
        RoleId roleId = new RoleId();

        assertNotNull(roleId.getValue());
    }

    @Test
    void test_StringArgConstructor() {
        String expected = UUID.randomUUID().toString();
        RoleId roleId = new RoleId(expected);

        assertEquals(expected, roleId.toString());
    }

    @Test
    void test_UuidArgConstructor() {
        UUID expected = UUID.randomUUID();
        RoleId roleId = new RoleId(expected);

        assertEquals(expected, roleId.getValue());
    }

    @Test
    void test_StringArgConstructor_WhenInvalidValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new RoleId("123")
        );
    }

    @Test
    void test_UuidArgConstructor_WhenNullValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new RoleId((UUID) null)
        );
    }

}