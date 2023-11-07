package dev.amir.userquery.framework.input.rabbitmq.mapper;

import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.valueobject.UserStatus;
import dev.amir.userquery.framework.input.rabbitmq.message.SaveUserMessage;
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
    void test_Convert() {
        SaveUserMessage message = new SaveUserMessage(
                "Id",
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE
        );

        User actual = underTest.convert(message);

        assertNotNull(actual);
        assertEquals(message.id(), actual.getId());
        assertEquals(message.name(), actual.getName());
        assertEquals(message.lastname(), actual.getLastname());
        assertEquals(message.email(), actual.getEmail());
        assertEquals(message.status(), actual.getStatus());
    }

    @Test
    void test_Convert_WhenStatusFieldIsNotPresent_SetInactiveByDefault() {
        SaveUserMessage message = new SaveUserMessage(
                "Id",
                "Name",
                "Lastname",
                "Email",
                UserStatus.INACTIVE
        );

        User actual = underTest.convert(message);

        assertNotNull(actual);
        assertEquals(message.id(), actual.getId());
        assertEquals(message.name(), actual.getName());
        assertEquals(message.lastname(), actual.getLastname());
        assertEquals(message.email(), actual.getEmail());
        assertEquals("INACTIVE", actual.getStatus().name());
    }

    @Test
    void test_Convert_WhenMessageIsNull_ReturnsNull() {
        User actual = underTest.convert(null);

        assertNull(actual);
    }
}