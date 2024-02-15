package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.input.rest.mapper.UserRequestMapper;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyProfileHandlerImpl implements MyProfileHandler {
    private final UserInputPort userInputPort;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void handle(ChangePasswordRequest request, UserId userIdParam) {
        userInputPort.changeUserPassword(userIdParam, request.password());
        log.info("Password changed for user with ID: {}", userIdParam);
    }

    @Override
    public void handle(UpdateProfileRequest request, UserId userIdParam) {
        User user = userRequestMapper.convert(request);
        user.setId(userIdParam);
        userInputPort.updateUserProfile(user);
        log.info("User with ID: {} Updated", userIdParam);
    }
}
