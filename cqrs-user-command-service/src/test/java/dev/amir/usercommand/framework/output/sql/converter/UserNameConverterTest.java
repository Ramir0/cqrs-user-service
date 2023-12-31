package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNameConverterTest {
    private UserNameConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserNameConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserName name = RandomObject.nextObject(UserName.class);

        String actual = underTest.convertToDatabaseColumn(name);

        assertEquals(name.value(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        String name = RandomObject.nextObject(String.class);

        UserName actual = underTest.convertToEntityAttribute(name);

        assertEquals(name, actual.value());
    }
}
