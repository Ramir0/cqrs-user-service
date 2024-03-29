package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.BirthDate;
import dev.amir.usercommand.util.RandomObject;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BirthDateConverterTest {
    private UserBirthDateConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserBirthDateConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        BirthDate birthDate = RandomObject.nextObject(BirthDate.class);

        LocalDate actual = underTest.convertToDatabaseColumn(birthDate);

        assertEquals(birthDate.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        LocalDate actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        LocalDate birthDate = RandomObject.nextObject(LocalDate.class);

        BirthDate actual = underTest.convertToEntityAttribute(birthDate);

        assertEquals(birthDate, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        BirthDate actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
