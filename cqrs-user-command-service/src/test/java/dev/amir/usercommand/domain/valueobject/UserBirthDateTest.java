package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserBirthDateTest {

    @Test
    void test_StringArgConstructor() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        UserBirthDate userBirthDate = new UserBirthDate(expected);

        assertEquals(expected, userBirthDate.value());
    }

    @Test
    void test_ToString_ReturnsValue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        UserBirthDate actual = new UserBirthDate(expected);

        assertEquals(expected, actual.value());
    }

    @Test
    void test_GetValue_ReturnsValue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        UserBirthDate birthDate = new UserBirthDate(expected);

        assertEquals(expected, birthDate.value());
    }

    @Test
    void test_Equals_IsTrue() {
        LocalDate expected = RandomObject.nextObject(LocalDate.class);

        UserBirthDate birthDate1 = new UserBirthDate(expected);
        UserBirthDate birthDate2 = new UserBirthDate(expected);

        assertEquals(birthDate1, birthDate2);
    }
}
