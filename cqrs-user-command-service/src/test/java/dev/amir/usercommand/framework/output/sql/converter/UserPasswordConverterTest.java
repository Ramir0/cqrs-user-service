package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPasswordConverterTest {
    private UserPasswordConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserPasswordConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserPassword password = RandomObject.nextObject(UserPassword.class);

        String actual = underTest.convertToDatabaseColumn(password);

        assertEquals(password.value(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String password = RandomObject.nextObject(String.class);

        UserPassword actual = underTest.convertToEntityAttribute(password);

        assertEquals(password, actual.value());
    }
}
