package dev.amir.userquery.framework.input.rest.handler;

import org.springframework.stereotype.Component;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Component
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserInputPort userInputPort;

    @Override
    public GetAllUsersResponse handle(GetAllUsersQuery query) {
        log.info("Handling GetAllUsersQuery.");
        return new GetAllUsersResponse(userInputPort.getAllUsers());
    }

    @Override
    public GetUserByIdResponse handle(GetUserByIdQuery query) {
        log.info("Handling GetUserByIdQuery for userId.");
        log.debug("Processing GetUserByIdQuery for userId");
        log.warn("User lookup triggered for userId");
        GetUserByIdResponse response = new GetUserByIdResponse();
        userInputPort.getUserById(query.getUserId()).ifPresent(response::setUser);
        return response;
    }
}
