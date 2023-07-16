package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.framework.input.rest.query.GetAllUsersQuery;
import dev.amir.userquery.framework.input.rest.query.GetUserByIdQuery;
import dev.amir.userquery.framework.input.rest.response.GetAllUsersResponse;
import dev.amir.userquery.framework.input.rest.response.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserInputPort userInputPort;

    @Override
    public GetAllUsersResponse handle(GetAllUsersQuery query) {
        return new GetAllUsersResponse(userInputPort.getAllUsers());
    }

    @Override
    public GetUserByIdResponse handle(GetUserByIdQuery query) {
        GetUserByIdResponse response = new GetUserByIdResponse();
        userInputPort.getUserById(query.getUserId()).ifPresent(response::setUser);
        return response;
    }
}
