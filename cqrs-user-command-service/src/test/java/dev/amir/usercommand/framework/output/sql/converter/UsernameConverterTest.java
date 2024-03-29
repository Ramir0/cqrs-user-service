package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsernameConverterTest {
    private UserUsernameConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserUsernameConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        Username username = RandomObject.nextObject(Username.class);

        String actual = underTest.convertToDatabaseColumn(username);

        assertEquals(username.value(), actual);
    }

    @Test
    void test_ConvertToDatabaseColumnWhenValueIsNull() {
        String actual = underTest.convertToDatabaseColumn(null);

        assertNull(actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String username = RandomObject.nextObject(String.class);

        Username actual = underTest.convertToEntityAttribute(username);

        assertEquals(username, actual.value());
    }

    @Test
    void test_ConvertToEntityAttributeWhenValueIsNull() {
        Username actual = underTest.convertToEntityAttribute(null);

        assertNull(actual);
    }
}
