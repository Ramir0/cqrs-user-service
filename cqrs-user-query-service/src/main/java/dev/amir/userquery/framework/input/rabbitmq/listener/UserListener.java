package dev.amir.userquery.framework.input.rabbitmq.listener;

import dev.amir.userquery.framework.input.rabbitmq.handler.UserMessageHandler;
import dev.amir.userquery.framework.input.rabbitmq.message.CreateUserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserListener {
    private final UserMessageHandler userMessageHandler;

    @RabbitListener(queues = {"${spring.rabbitmq.queues.users-queue}"})
    public void onUser(CreateUserMessage message) {
        userMessageHandler.handle(message);
    }
}
