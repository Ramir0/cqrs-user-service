package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.input.rest.mapper.UserRequestMapper;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {
    private final UserInputPort userInputPort;
    private final UserRequestMapper requestMapper;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        User user = requestMapper.convert(request);
        UserId userId = userInputPort.createUser(user);

        log.info("User with ID: {} created", userId);
        return new CreateUserResponse(userId.toString());
    }

    @Override
    public void handle(UpdateUserRequest request, UUID userIdParam) {
        User user = requestMapper.convert(request);
        UserId userId = new UserId(userIdParam);
        user.setId(userId);
        userInputPort.updateUser(user);
        log.info("User with ID: {} Updated", userId);
    }

    @Override
    public boolean handle(DeleteUserRequest request, UUID userId) {
        boolean deleted = userInputPort.deleteUser(userId);
        log.info("verifying deletion of user with ID {} ", userId);
        return deleted;
    }
}
