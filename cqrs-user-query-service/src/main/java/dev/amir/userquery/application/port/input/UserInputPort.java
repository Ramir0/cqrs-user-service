package dev.amir.userquery.application.port.input;

import dev.amir.userquery.domain.entity.User;
import java.util.Collection;

public interface UserInputPort {
    Collection<User> getAllUsers();

    User getUserById(String userId);
}
