package dev.amir.userquery.framework.input.rest.handler;

import dev.amir.userquery.application.port.input.UserInputPort;
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
    public GetAllUsersResponse getAllUsers() {
        return new GetAllUsersResponse(userInputPort.getAllUsers());
    }

    @Override
    public GetUserByIdResponse getUserById(GetUserByIdQuery query) {
        GetUserByIdResponse response = new GetUserByIdResponse();
        userInputPort.getUserById(query.getUserId()).ifPresent(response::setUser);
        return response;
    }
}
