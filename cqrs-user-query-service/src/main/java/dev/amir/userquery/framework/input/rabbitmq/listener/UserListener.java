package dev.amir.userquery.framework.input.rabbitmq.listener;

import dev.amir.userquery.framework.input.rabbitmq.handler.UserMessageHandler;
import dev.amir.userquery.framework.input.rabbitmq.message.SaveUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserListener {
    private final UserMessageHandler userMessageHandler;

    @RabbitListener(queues = {"${spring.rabbitmq.queues.users-queue}"})
    public void onSaveUser(SaveUserMessage message) {
        log.info("Received message: {}", message);
        userMessageHandler.handle(message);
    }
}
