package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PasswordTest {

    @Test
    void test_StringArgConstructor() {
        String password = RandomObject.nextObject(String.class);

        Password userPassword = new Password(password);

        assertNotNull(userPassword);
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Password password = new Password(expected);

        assertEquals(expected, password.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Password password = new Password(expected);

        assertEquals(expected, password.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        Password password1 = new Password(expected);
        Password password2 = new Password(expected);

        assertEquals(password1, password2);
    }
}
