package dev.amir.usercommand.framework.output.rabbitmq.producer;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.rabbitmq.mapper.UserMessageMapper;
import dev.amir.usercommand.framework.output.rabbitmq.message.CreateUserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMessageProducer implements UserMessageOutputPort {
    private final RabbitTemplate rabbitTemplate;
    private final UserMessageMapper userMessageMapper;

    @Value("${spring.rabbitmq.queues.users-queue}")
    private String usersQueue;

    @Override
    public void sendMessage(User user) {
        CreateUserMessage message = userMessageMapper.convert(user);
        rabbitTemplate.convertAndSend(usersQueue, message);
    }
}
