package dev.amir.usercommand.framework.output.rabbitmq.producer;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.rabbitmq.mapper.UserMessageMapper;
import dev.amir.usercommand.framework.output.rabbitmq.message.SaveUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMessageProducer implements UserMessageOutputPort {
    private final RabbitTemplate rabbitTemplate;
    private final UserMessageMapper userMessageMapper;

    @Value("${spring.rabbitmq.queues.users-queue}")
    private String usersQueue;

    @Override
    public void sendMessage(User user) {
        SaveUserMessage message = userMessageMapper.convert(user);
        rabbitTemplate.convertAndSend(usersQueue, message);
        log.info("Message sent for user: {}", user.getName());
    }
}
