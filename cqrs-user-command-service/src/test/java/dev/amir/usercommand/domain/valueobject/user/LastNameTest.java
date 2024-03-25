package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LastNameTest {
    @Test
    void test_StringArgConstructor() {
        String lastName = RandomObject.nextObject(String.class);

        LastName userLastName = new LastName(lastName);

        assertNotNull(userLastName);
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        LastName actual = new LastName(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        LastName lastName = new LastName(expected);

        assertEquals(expected, lastName.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        LastName lastName1 = new LastName(expected);
        LastName lastName2 = new LastName(expected);

        assertEquals(lastName1, lastName2);
    }
}
