package dev.amir.usercommand.domain.valueobject.user;

import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirthDateTest {

    @Test
    void test_StringArgConstructor() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        BirthDate birthDate = new BirthDate(expected);

        assertEquals(expected, birthDate.value());
    }

    @Test
    void test_ToString_ReturnsValue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        BirthDate actual = new BirthDate(expected);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        BirthDate birthDate = new BirthDate(expected);

        assertEquals(expected, birthDate.value());
    }

    @Test
    void test_Equals_IsTrue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        BirthDate birthDate1 = new BirthDate(expected);
        BirthDate birthDate2 = new BirthDate(expected);

        assertEquals(birthDate1, birthDate2);
    }
}
