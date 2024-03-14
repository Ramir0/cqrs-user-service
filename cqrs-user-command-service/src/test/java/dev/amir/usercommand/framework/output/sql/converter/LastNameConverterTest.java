package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LastNameConverterTest {
    private UserLastNameConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserLastNameConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        LastName lastName = RandomObject.nextObject(LastName.class);

        String actual = underTest.convertToDatabaseColumn(lastName);

        assertEquals(lastName.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        String actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String lastName = RandomObject.nextObject(String.class);

        LastName actual = underTest.convertToEntityAttribute(lastName);

        assertEquals(lastName, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        LastName actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
