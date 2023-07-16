package dev.amir.usercommand.application.port.input;

import dev.amir.usercommand.domain.entity.User;

public interface UserInputPort {
    String createUser(User user);

    void updateUser(User user);

    boolean deleteUser(String userId);
}
