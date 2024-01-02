package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserUsernameTest {

    @Test
    void test_StringArgConstructor() {
        String username = RandomObject.nextObject(String.class);

        UserUsername userUsername = new UserUsername(username);

        assertNotNull(userUsername);
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserUsername actual = new UserUsername(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserUsername actual = new UserUsername(expected);

        assertEquals(expected, actual.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        UserUsername actual1 = new UserUsername(expected);
        UserUsername actual2 = new UserUsername(expected);

        assertEquals(actual1, actual2);
    }
}
