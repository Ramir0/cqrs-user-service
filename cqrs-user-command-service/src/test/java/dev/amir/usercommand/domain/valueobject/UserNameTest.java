package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNameTest {

    @Test
    void test_StringArgConstructor() {
        String expected = RandomObject.nextObject(String.class);

        UserName name = new UserName(expected);

        assertEquals(expected, name.toString());
    }

    @Test
    void test_ToString_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserName actual = new UserName(expected);

        assertEquals(expected, actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        String expected = RandomObject.nextObject(String.class);

        UserName name = new UserName(expected);

        assertEquals(expected, name.value());
    }

    @Test
    void test_Equals_IsTrue() {
        String expected = RandomObject.nextObject(String.class);

        UserName name1 = new UserName(expected);
        UserName name2 = new UserName(expected);

        assertEquals(name1, name2);
    }
}
