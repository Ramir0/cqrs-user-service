package dev.amir.userquery.application.port.output;

import dev.amir.userquery.domain.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserOutputPort {
    Collection<User> getAll();
    Optional<User> getById(String userId);
    User save(User user);
}
