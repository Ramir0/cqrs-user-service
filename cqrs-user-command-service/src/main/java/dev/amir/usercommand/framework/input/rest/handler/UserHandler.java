package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;
import java.util.UUID;

public interface UserHandler {
    CreateUserResponse handle(CreateUserRequest request);

    void handle(UpdateUserRequest request, UUID userId);

    void handle(DeleteUserRequest request, UUID userId);
}
