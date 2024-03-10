package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;

public interface MyProfileHandler {
    void handle(ChangePasswordRequest request, UserId userId);

    void handle(UpdateProfileRequest request, UserId userId);
}
