package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTest {

    @Test
    void test_StringArgConstructor() {
        String expected = RandomObject.nextObject(String.class);

        Email email = new Email(expected);

        assertEquals(expected, email.toString());
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Email actual = new Email(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        Email email = new Email(expected);

        assertEquals(expected, email.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        Email email1 = new Email(expected);
        Email email2 = new Email(expected);

        assertEquals(email1, email2);
    }
}
