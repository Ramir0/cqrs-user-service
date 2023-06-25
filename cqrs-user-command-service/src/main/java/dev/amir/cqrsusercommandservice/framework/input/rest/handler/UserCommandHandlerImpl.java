package dev.amir.cqrsusercommandservice.framework.input.rest.handler;

import dev.amir.cqrsusercommandservice.application.port.input.UserInputPort;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.mapper.UserCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCommandHandlerImpl implements UserCommandHandler {
    private final UserInputPort userInputPort;
    private final UserCommandMapper commandMapper;

    @Override
    public String handle(CreateUserCommand createUserCommand) {
        return userInputPort.createUser(commandMapper.convert(createUserCommand));
    }
}
