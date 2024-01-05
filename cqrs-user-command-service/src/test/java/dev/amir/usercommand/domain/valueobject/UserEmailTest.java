package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEmailTest {

    @Test
    void test_StringArgConstructor() {
        String expected = RandomObject.nextObject(String.class);

        UserEmail email = new UserEmail(expected);

        assertEquals(expected, email.toString());
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserEmail actual = new UserEmail(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserEmail email = new UserEmail(expected);

        assertEquals(expected, email.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        UserEmail email1 = new UserEmail(expected);
        UserEmail email2 = new UserEmail(expected);

        assertEquals(email1, email2);
    }
}
