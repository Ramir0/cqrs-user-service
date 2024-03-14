package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmailConverterTest {
    private UserEmailConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserEmailConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        Email email = RandomObject.nextObject(Email.class);

        String actual = underTest.convertToDatabaseColumn(email);

        assertEquals(email.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        String actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String userEmail = RandomObject.nextObject(String.class);

        Email actual = underTest.convertToEntityAttribute(userEmail);

        assertEquals(userEmail, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        Email actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
