package dev.amir.usercommand.framework.input.rest.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        CreateUserRequest expected = RandomObject.nextObject(CreateUserRequest.class);

        User actual = underTest.convert(expected);

        assertEquals(expected.roleId(), actual.getRoleId());
        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
    }

    @Test
    void test_Convert_WhenCreateUserRequestIsNull_ReturnsNull() {
        User actual = underTest.convert((CreateUserRequest) null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromUpdateUserRequest_ToUser() {
        UpdateUserRequest expected = RandomObject.nextObject(UpdateUserRequest.class);

        User actual = underTest.convert(expected);

        assertEquals(expected.roleId(), actual.getRoleId());
        assertEquals(expected.name(), actual.getName());
        assertEquals(expected.lastname(), actual.getLastname());
        assertEquals(expected.email(), actual.getEmail());
    }

    @Test
    void test_Convert_WhenUpdateUserRequestIsNull_ReturnsNull() {
        User actual = underTest.convert((UpdateUserRequest) null);

        assertNull(actual);
    }
}
