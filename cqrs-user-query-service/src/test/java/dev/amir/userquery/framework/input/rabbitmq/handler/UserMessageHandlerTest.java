package dev.amir.userquery.framework.input.rabbitmq.handler;

import dev.amir.userquery.application.port.input.UserCommandInputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import dev.amir.userquery.framework.input.rabbitmq.mapper.UserMessageMapper;
import dev.amir.userquery.framework.input.rabbitmq.message.SaveUserMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMessageHandlerTest {
    @Mock
    private UserCommandInputPort userCommandInputPortMock;
    @Mock
    private UserMessageMapper userMessageMapperMock;

    @InjectMocks
    private UserMessageHandlerImpl underTest;

    @Test
    void test_Handle() {
        SaveUserMessage message = new SaveUserMessage(
                "Id",
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE,
                UserGender.MALE
        );
        User user = new User();
        when(userMessageMapperMock.convert(any(SaveUserMessage.class))).thenReturn(user);
        doNothing().when(userCommandInputPortMock).saveUser(user);

        underTest.handle(message);

        verify(userMessageMapperMock).convert(eq(message));
        verify(userCommandInputPortMock).saveUser(eq(user));
    }
}
