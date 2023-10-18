package dev.amir.userquery.application.port.input;

import dev.amir.userquery.domain.entity.User;

public interface UserCommandInputPort {
    void saveUser(User user);
}
