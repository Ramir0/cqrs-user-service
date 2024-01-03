package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLastNameConverterTest {
    private UserLastNameConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserLastNameConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserLastName lastName = RandomObject.nextObject(UserLastName.class);

        String actual = underTest.convertToDatabaseColumn(lastName);

        assertEquals(lastName.value(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String lastName = RandomObject.nextObject(String.class);

        UserLastName actual = underTest.convertToEntityAttribute(lastName);

        assertEquals(lastName, actual.value());
    }
}
