package dev.amir.userquery.framework.input.rabbitmq.handler;

import dev.amir.userquery.application.port.input.UserCommandInputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rabbitmq.mapper.UserMessageMapper;
import dev.amir.userquery.framework.input.rabbitmq.message.CreateUserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMessageHandlerImpl implements UserMessageHandler {
    private final UserCommandInputPort userCommandInputPort;
    private final UserMessageMapper userMessageMapper;

    @Override
    public void handle(CreateUserMessage message) {
        User user = userMessageMapper.convert(message);
        userCommandInputPort.createUser(user);
    }
}
