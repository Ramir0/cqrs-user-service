package dev.amir.usercommand.application.port.input;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.UserPassword;


public interface UserInputPort {
    UserId createUser(User user);

    void updateUser(User user);

    void deleteUser(UserId userId);

    void changeUserPassword(UserId userIdParam, UserPassword password);

    void updateUserProfile(User user);
}
