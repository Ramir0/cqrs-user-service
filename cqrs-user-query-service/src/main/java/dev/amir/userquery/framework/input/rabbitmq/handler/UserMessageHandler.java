package dev.amir.userquery.framework.input.rabbitmq.handler;

import dev.amir.userquery.framework.input.rabbitmq.message.SaveUserMessage;

public interface UserMessageHandler {
    void handle(SaveUserMessage message);
}
