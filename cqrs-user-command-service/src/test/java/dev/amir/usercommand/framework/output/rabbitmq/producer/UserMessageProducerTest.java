package dev.amir.usercommand.framework.output.rabbitmq.producer;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.framework.output.rabbitmq.mapper.UserMessageMapper;
import dev.amir.usercommand.framework.output.rabbitmq.message.SaveUserMessage;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMessageProducerTest {
    @Mock
    private RabbitTemplate rabbitTemplateMock;
    @Mock
    private UserMessageMapper userMessageMapperMock;

    @InjectMocks
    private UserMessageProducer underTest;

    @Test
    void test_SendMessage() {
        String queueName = "test-queue";
        ReflectionTestUtils.setField(underTest, "usersQueue", queueName);

        User user = new User();
        SaveUserMessage message = new SaveUserMessage(
                UUID.randomUUID().toString(),
                "Name",
                "Lastname",
                "Email",
                UserStatus.ACTIVE
        );

        when(userMessageMapperMock.convert(any(User.class))).thenReturn(message);
        doNothing().when(rabbitTemplateMock).convertAndSend(anyString(), any(SaveUserMessage.class));

        underTest.sendMessage(user);

        verify(userMessageMapperMock).convert(user);
        verify(rabbitTemplateMock).convertAndSend(eq(queueName), eq(message));
    }
}
