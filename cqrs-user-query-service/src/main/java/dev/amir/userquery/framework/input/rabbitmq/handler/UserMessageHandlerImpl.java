package dev.amir.userquery.framework.input.rabbitmq.handler;

import org.springframework.stereotype.Component;

import dev.amir.userquery.application.port.input.UserCommandInputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rabbitmq.mapper.UserMessageMapper;
import dev.amir.userquery.framework.input.rabbitmq.message.SaveUserMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMessageHandlerImpl implements UserMessageHandler {
    private final UserCommandInputPort userCommandInputPort;
    private final UserMessageMapper userMessageMapper;

    @Override
    public void handle(SaveUserMessage message) {
        log.info("Handling user message.");
        User user = userMessageMapper.convert(message);
        log.info("Converting message to user.");
        userCommandInputPort.createUser(user);
        log.info("Created user.");
    }
}
