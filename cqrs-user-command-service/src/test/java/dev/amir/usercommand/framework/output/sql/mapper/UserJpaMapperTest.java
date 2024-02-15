package dev.amir.usercommand.framework.output.sql.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class UserJpaMapperTest {

    private UserJpaMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(UserJpaMapper.class);
    }

    @Test
    void test_Convert_FromUser_ToUserJpa() {
        User expected = RandomObject.nextObject(User.class);

        UserJpa actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getRoleId().getValue(), actual.getRoleId());
        assertEquals(expected.getId().getValue().toString(), actual.getId().toString());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void test_Convert_WhenUser_ReturnsNull() {
        UserJpa actual = underTest.convert((User) null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromUserJpa_ToUser() {
        UserJpa expected = RandomObject.nextObject(UserJpa.class);

        User actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getRoleId(), actual.getRoleId().getValue());
        assertEquals(expected.getId().toString(), actual.getId().getValue().toString());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void test_Convert_WhenUserJpa_ReturnsNull() {
        User actual = underTest.convert((UserJpa) null);

        assertNull(actual);
    }

    @Test
    void test_RoleIdToUuid() {
        RoleId expected = new RoleId();

        UUID actual = underTest.roleIdToUuid(expected);

        assertEquals(expected.getValue(), actual);
    }

    @Test
    void test_UuidToRoleId() {
        UUID expected = UUID.randomUUID();

        RoleId actual = underTest.uuidToRoleId(expected);

        assertEquals(expected, actual.getValue());
    }

    @Test
    void test_RoleIdToUuid_WhenRoleIdIsNull_ReturnsNull() {
        UUID actual = underTest.roleIdToUuid(null);

        assertNull(actual);
    }

    @Test
    void test_UuidToRoleId_WhenUuidIsNull_ReturnsNull() {
        RoleId actual = underTest.uuidToRoleId(null);

        assertNull(actual);
    }
}
