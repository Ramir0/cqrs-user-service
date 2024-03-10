package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.CreateUserRequest;
import dev.amir.usercommand.framework.input.rest.request.DeleteUserRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateUserRequest;
import dev.amir.usercommand.framework.input.rest.response.CreateUserResponse;

public interface UserHandler {
    CreateUserResponse handle(CreateUserRequest request);

    void handle(UpdateUserRequest request, UserId userId);

    void handle(DeleteUserRequest request, UserId userId);

    void handle(ChangePasswordRequest request, UserId userId);
}
