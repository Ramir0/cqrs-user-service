package dev.amir.usercommand.domain.valueobject;

import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(expected.toString(), actual.toString());
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
