package dev.amir.usercommand.framework.output.sql.converter;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleIdConverterTest {
    private RoleIdConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoleIdConverter();
    }

    @Test
    void test_ConvertToDatabaseColumn() {
        RoleId roleId = RandomObject.nextObject(RoleId.class);

        UUID actual = underTest.convertToDatabaseColumn(roleId);

        assertEquals(roleId.getValue(), actual);
    }

    @Test
    void test_ConvertToEntityAttribute() {
        UUID uuid = RandomObject.nextObject(UUID.class);

        RoleId actual = underTest.convertToEntityAttribute(uuid);

        assertEquals(uuid, actual.getValue());
    }
}
