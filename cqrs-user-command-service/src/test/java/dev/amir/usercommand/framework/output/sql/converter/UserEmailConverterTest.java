package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserEmailConverterTest {
    private UserEmailConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserEmailConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserEmail userEmail = RandomObject.nextObject(UserEmail.class);

        String actual = underTest.convertToDatabaseColumn(userEmail);

        assertEquals(userEmail.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        String actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String userEmail = RandomObject.nextObject(String.class);

        UserEmail actual = underTest.convertToEntityAttribute(userEmail);

        assertEquals(userEmail, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        UserEmail actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
