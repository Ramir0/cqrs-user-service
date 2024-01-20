package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import java.util.UUID;

public interface MyProfileHandler {
    void handle(ChangePasswordRequest request, UUID userId);
}
