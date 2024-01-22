package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;
import java.util.UUID;

public interface MyProfileHandler {
    void handle(ChangePasswordRequest request, UUID userId);

    void handle(UpdateProfileRequest request, UUID userId);
}
