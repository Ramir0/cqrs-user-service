package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FirstNameConverterTest {
    private UserNameConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserNameConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        FirstName name = RandomObject.nextObject(FirstName.class);

        String actual = underTest.convertToDatabaseColumn(name);

        assertEquals(name.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        String actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String name = RandomObject.nextObject(String.class);

        FirstName actual = underTest.convertToEntityAttribute(name);

        assertEquals(name, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        FirstName actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
