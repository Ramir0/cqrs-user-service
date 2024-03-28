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
        log.info("Handling get all users");
        return new GetAllUsersResponse(userInputPort.getAllUsers());
    }

    @Override
    public GetUserByIdResponse handle(GetUserByIdQuery query) {
        log.info("Handling get user by ID: {}", query.userId());
        User user = userInputPort.getUserById(query.userId());
        return new GetUserByIdResponse(user.getId(), user.getName(), user.getLastname(), user.getEmail(),
                user.getStatus(), user.getUsername(), user.getGender(),
                user.getBirthDate(), user.getCreatedAt(), user.getRole().getName());
    }
}
