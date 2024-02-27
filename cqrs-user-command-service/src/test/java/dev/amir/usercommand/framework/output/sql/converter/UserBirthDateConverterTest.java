package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserBirthDate;
import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserBirthDateConverterTest {
    private UserBirthDateConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserBirthDateConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserBirthDate birthDate = RandomObject.nextObject(UserBirthDate.class);

        LocalDate actual = underTest.convertToDatabaseColumn(birthDate);

        assertEquals(birthDate.value(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        LocalDate birthDate = RandomObject.nextObject(LocalDate.class);

        UserBirthDate actual = underTest.convertToEntityAttribute(birthDate);

        assertEquals(birthDate, actual.value());
    }
}
