package dev.amir.usercommand.framework.output.rabbitmq.mapper;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.framework.output.rabbitmq.message.SaveUserMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class UserMessageMapperTest {

    private UserMessageMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(UserMessageMapper.class);
    }

    @Test
    void test_Convert_FromUser_ToSaveUserMessage() {
        User user = new User();
        user.setId(new UserId());
        user.setName("Name");
        user.setLastname("Lastname");
        user.setEmail("Email");
        user.setStatus(UserStatus.ACTIVE);

        SaveUserMessage actual = underTest.convert(user);

        assertNotNull(actual);
        assertEquals(user.getId().toString(), actual.id());
        assertEquals(user.getName(), actual.name());
        assertEquals(user.getLastname(), actual.lastname());
        assertEquals(user.getEmail(), actual.email());
        assertEquals(user.getStatus(), actual.status());
    }

    @Test
    void test_Convert_FromUserNull_ReturnsNull() {
        SaveUserMessage actual = underTest.convert(null);

        assertNull(actual);
    }

    @Test
    void test_UserIdToString() {
        UserId userId = new UserId();

        String actual = underTest.userIdToString(userId);

        assertEquals(userId.toString(), actual);
    }
}
