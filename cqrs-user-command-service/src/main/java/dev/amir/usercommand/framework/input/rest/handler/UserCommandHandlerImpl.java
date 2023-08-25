package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.input.rest.command.CreateUserCommand;
import dev.amir.usercommand.framework.input.rest.command.DeleteUserCommand;
import dev.amir.usercommand.framework.input.rest.command.UpdateUserCommand;
import dev.amir.usercommand.framework.input.rest.mapper.UserCommandMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCommandHandlerImpl implements UserCommandHandler {
    private final UserInputPort userInputPort;
    private final UserCommandMapper commandMapper;

    @Override
    public String handle(CreateUserCommand createUserCommand) {
        User user = commandMapper.convert(createUserCommand);
        String userId = userInputPort.createUser(user);

        log.info("User with ID: {} created", userId);
        return userId;
    }

    @Override
    public void handle(UpdateUserCommand updateUserCommand, String userId) {
        User user = commandMapper.convert(updateUserCommand);
        user.setId(userId);
        userInputPort.updateUser(user);
        log.info("User  with ID: {} Updated", userId);
    }

    @Override
    public boolean handle(DeleteUserCommand command, String userId) {
        boolean deleted = userInputPort.deleteUser(userId);

        if (deleted) {
            log.info("User with ID {} deleted", userId);
        } else {
            log.warn("Failed to delete user");
        }

        return deleted;
    }
}
