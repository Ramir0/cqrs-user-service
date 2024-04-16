package dev.amir.usercommand.domain.valueobject.permission;

import dev.amir.usercommand.domain.exception.UserValidationException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PermissionIdTest {
    @Test
    void test_NoArgsConstructor() {
        PermissionId permissionId = new PermissionId();

        assertNotNull(permissionId.getValue());
    }

    @Test
    void test_StringArgConstructor() {
        String expected = UUID.randomUUID().toString();
        PermissionId permissionId = new PermissionId(expected);

        assertEquals(expected, permissionId.toString());
    }

    @Test
    void test_UuidArgConstructor() {
        UUID expected = UUID.randomUUID();
        PermissionId permissionId = new PermissionId(expected);

        assertEquals(expected, permissionId.getValue());
    }

    @Test
    void test_StringArgConstructor_WhenInvalidValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new PermissionId("123")
        );
    }

    @Test
    void test_UuidArgConstructor_WhenNullValue_ThrowsException() {
        assertThrows(
                UserValidationException.class,
                () -> new PermissionId((UUID) null)
        );
    }
}
