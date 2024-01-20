package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyProfileHandlerImpl implements MyProfileHandler {
    private final UserInputPort userInputPort;

    @Override
    public void handle(ChangePasswordRequest request, UUID userIdParam) {
        userInputPort.changeUserPassword(userIdParam, request.password());
        log.info("Password changed for user with ID: {}", userIdParam);
    }
}
