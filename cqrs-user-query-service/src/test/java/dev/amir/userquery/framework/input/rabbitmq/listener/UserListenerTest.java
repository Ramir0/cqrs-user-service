package dev.amir.userquery.framework.input.rabbitmq.listener;

import dev.amir.userquery.framework.input.rabbitmq.handler.UserMessageHandler;
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

@ExtendWith(MockitoExtension.class)
class UserListenerTest {

    @Mock
    private UserMessageHandler userMessageHandlerMock;

    @InjectMocks
    private UserListener underTest;

    @Test
    void test_OnSaveUser() {
        SaveUserMessage message = new SaveUserMessage(
                "Id",
                "Name",
                "Lastname",
                "Email",
                true
        );
        doNothing().when(userMessageHandlerMock).handle(any(SaveUserMessage.class));

        underTest.onSaveUser(message);

        verify(userMessageHandlerMock).handle(eq(message));
    }
}
