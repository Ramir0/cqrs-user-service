package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIdConverterTest {
    private UserIdConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserIdConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        UserId userId = RandomObject.nextObject(UserId.class);

        UUID actual = underTest.convertToDatabaseColumn(userId);

        assertEquals(userId.getValue(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        UUID uuid = RandomObject.nextObject(UUID.class);

        UserId actual = underTest.convertToEntityAttribute(uuid);

        assertEquals(uuid, actual.getValue());
    }
}
