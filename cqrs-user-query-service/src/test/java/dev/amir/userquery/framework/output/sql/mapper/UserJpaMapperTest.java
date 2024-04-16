package dev.amir.userquery.framework.output.sql.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import util.RandomObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserJpaMapperTest {

    private UserEntityMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(UserEntityMapper.class);
    }

    @Test
    public void test_Convert_FromUser_ToUserEntity() {
        User expectedUser = RandomObject.nextObject(User.class);

        UserJpa actual = underTest.convert(expectedUser);

        assertNotNull(actual);
        assertEquals(expectedUser.getId(), actual.getId());
        assertEquals(expectedUser.getName(), actual.getName());
        assertEquals(expectedUser.getLastname(), actual.getLastname());
        assertEquals(expectedUser.getEmail(), actual.getEmail());
        assertEquals(expectedUser.getStatus(), actual.getStatus());
        assertEquals(expectedUser.getGender(), actual.getGender());
    }

    @Test
    public void test_Convert_FromUserEntity_ToUser() {
        UserJpa expected = RandomObject.nextObject(UserJpa.class);

        User actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getGender(), actual.getGender());
    }

    @Test
    void test_Convert_FromUserNull_ToUserEntityNull() {
        UserJpa actual = underTest.convert((User) null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromUserEntityNull_ToUserNull() {
        User actual = underTest.convert((UserJpa) null);

        assertNull(actual);
    }
}
