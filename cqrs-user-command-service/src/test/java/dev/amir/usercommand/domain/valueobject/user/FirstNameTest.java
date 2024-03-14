package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FirstNameTest {

    @Test
    void test_StringArgConstructor() {
        String name = RandomObject.nextObject(String.class);

        FirstName firstName = new FirstName(name);

        assertNotNull(firstName);
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        FirstName actual = new FirstName(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        FirstName name = new FirstName(expected);

        assertEquals(expected, name.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        FirstName name1 = new FirstName(expected);
        FirstName name2 = new FirstName(expected);

        assertEquals(name1, name2);
    }
}
