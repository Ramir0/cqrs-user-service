package dev.amir.usercommand.application.port.input;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import java.util.UUID;


public interface UserInputPort {
    UserId createUser(User user);

    void updateUser(User user);

    void deleteUser(UUID userId);

    void changeUserPassword(UUID userIdParam, UserPassword password);
}
