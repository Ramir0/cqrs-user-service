package dev.amir.userquery.application.port.input;

import dev.amir.userquery.domain.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserInputPort {
    Collection<User> getAllUsers();
    Optional<User> getUserById(String userId);
}
