package dev.amir.userquery.framework.input.rabbitmq.handler;

import dev.amir.userquery.framework.input.rabbitmq.message.CreateUserMessage;

public interface UserMessageHandler {
    void handle(CreateUserMessage message);
}
