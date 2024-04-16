package dev.amir.usercommand.domain.valueobject.role;

import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameTest {
    private Name underTest;

    @Test
    void test_ToString_ShouldReturnValue() {
        String name = RandomObject.nextObject(String.class);
        underTest = new Name(name);

        assertEquals(name, underTest.toString());
    }

    @Test
    void test_Value_ShouldReturnValue() {
        String name = RandomObject.nextObject(String.class);
        underTest = new Name(name);

        assertEquals(name, underTest.value());
    }
}
