package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLastNameTest {
    @Test
    void test_StringArgConstructor() {
        String expected = RandomObject.nextObject(String.class);

        UserLastName lastName = new UserLastName(expected);

        assertEquals(expected, lastName.toString());
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserLastName actual = new UserLastName(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserLastName lastName = new UserLastName(expected);

        assertEquals(expected, lastName.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        UserLastName lastName1 = new UserLastName(expected);
        UserLastName lastName2 = new UserLastName(expected);

        assertEquals(lastName1, lastName2);
    }
}
