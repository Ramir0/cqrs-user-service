package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserNameTest {

    @Test
    void test_StringArgConstructor() {
        String name = RandomObject.nextObject(String.class);

        UserName userName = new UserName(name);

        assertNotNull(userName);
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
