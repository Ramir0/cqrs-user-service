package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsernameTest {

    @Test
    void test_StringArgConstructor() {
        String username = RandomObject.nextObject(String.class);

        Username userName = new Username(username);

        assertNotNull(userName);
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Username actual = new Username(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Username actual = new Username(expected);

        assertEquals(expected, actual.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        Username actual1 = new Username(expected);
        Username actual2 = new Username(expected);

        assertEquals(actual1, actual2);
    }
}
