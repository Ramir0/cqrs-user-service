package dev.amir.usercommand.framework.input.rest.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class UserRequestMapperTest {

    private UserRequestMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(UserRequestMapper.class);
    }

    @Test
    void test_Convert_FromCreateUserRequest_ToUser() {
        CreateUserRequest expected = new CreateUserRequest(
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE,
                "UserName",
                UserGender.FEMALE
        );

        User actual = underTest.convert(expected);

        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
        assertEquals(expected.status(), actual.getStatus());
    }

    @Test
    void test_Convert_FromCreateUserRequest_WithActiveNull_ToUser() {
        CreateUserRequest expected = new CreateUserRequest(
                "Name",
                "Lastname",
                "Email",
                null,
                "UserName",
                UserGender.FEMALE
        );

        User actual = underTest.convert(expected);

        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
        assertNull(actual.getStatus());
    }

    @Test
    void test_Convert_WhenCreateUserRequestIsNull_ReturnsNull() {
        User actual = underTest.convert((CreateUserRequest) null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromUpdateUserRequest_ToUser() {
        UpdateUserRequest expected = new UpdateUserRequest(
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE,
                "UserName",
                UserGender.FEMALE
        );

        User actual = underTest.convert(expected);

        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
        assertEquals(expected.status(), actual.getStatus());
    }

    @Test
    void test_Convert_FromUpdateUserRequest_WithActiveNull_ToUser() {
        UpdateUserRequest expected = new UpdateUserRequest(
                "Name",
                "Lastname",
                "Email",
                null,
                "UserName",
                UserGender.FEMALE
        );

        User actual = underTest.convert(expected);

        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
        assertNull(actual.getStatus());
    }

    @Test
    void test_Convert_WhenUpdateUserRequestIsNull_ReturnsNull() {
        User actual = underTest.convert((UpdateUserRequest) null);

        assertNull(actual);
    }
}
