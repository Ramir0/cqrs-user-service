package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserInputPort userInputPort;

    @Override
    public GetAllUsersResponse handle(GetAllUsersQuery query) {
        log.info("Handling all users query");
        return new GetAllUsersResponse(userInputPort.getAllUsers());
    }

    @Override
    public GetUserByIdResponse handle(GetUserByIdQuery query) {
        log.info("Handling user by ID: {}", query.getUserId());
        User user = userInputPort.getUserById(query.getUserId());
        return new GetUserByIdResponse(user);
    }
}
