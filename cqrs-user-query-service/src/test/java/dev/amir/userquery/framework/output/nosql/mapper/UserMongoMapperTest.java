package dev.amir.userquery.framework.output.nosql.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.valueobject.UserStatus;
import dev.amir.userquery.framework.output.nosql.entity.UserMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class UserMongoMapperTest {

    private UserMongoMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(UserMongoMapper.class);
    }

    @Test
    void test_Convert_FromUser_ToUserMongo() {
        User expected = new User();
        expected.setId("Id");
        expected.setName("Name");
        expected.setLastname("Lastname");
        expected.setEmail("Email");
        expected.setStatus(UserStatus.ACTIVE);

        UserMongo actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void test_Convert_FromUserMongo_ToUser() {
        UserMongo expected = new UserMongo();
        expected.setId("Id");
        expected.setName("Name");
        expected.setLastname("Lastname");
        expected.setEmail("Email");
        expected.setStatus(UserStatus.ACTIVE);

        User actual = underTest.convert(expected);

        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void test_Convert_FromUserNull_ToUserMongoNull() {
        UserMongo actual = underTest.convert((User) null);

        assertNull(actual);
    }

    @Test
    void test_Convert_FromUserMongoNull_ToUserNull() {
        User actual = underTest.convert((UserMongo) null);

        assertNull(actual);
    }
}
