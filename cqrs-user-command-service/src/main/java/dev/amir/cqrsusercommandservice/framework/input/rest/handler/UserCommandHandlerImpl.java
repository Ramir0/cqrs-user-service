package dev.amir.cqrsusercommandservice.framework.input.rest.handler;

import dev.amir.cqrsusercommandservice.application.port.input.UserInputPort;
import dev.amir.cqrsusercommandservice.domain.entity.User;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.CreateUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.DeleteUserCommand;
import dev.amir.cqrsusercommandservice.framework.input.rest.command.UpdateUserCommand;
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

    @Override
    public void handle(UpdateUserCommand updateUserCommand, String userId) {
        User user = commandMapper.convert(updateUserCommand);
        user.setId(userId);
        userInputPort.updateUser(user);
    }

    @Override
    public boolean handle(DeleteUserCommand command, String userId) {
        return userInputPort.deleteUser(userId);
    }
}
