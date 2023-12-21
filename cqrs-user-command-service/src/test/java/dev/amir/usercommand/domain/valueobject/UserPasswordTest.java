package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPasswordTest {

    @Test
    void test_StringArgConstructor() {
        String expected = RandomObject.nextObject(String.class);

        UserPassword password = new UserPassword(expected);

        assertEquals(expected, password.toString());
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserPassword password = new UserPassword(expected);

        assertEquals(expected, password.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserPassword password = new UserPassword(expected);

        assertEquals(expected, password.getValue());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        UserPassword password1 = new UserPassword(expected);
        UserPassword password2 = new UserPassword(expected);

        assertEquals(password1, password2);
    }
}
