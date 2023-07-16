package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.User;

public interface UserOutputPort {
    User save(User user);
    boolean delete(String userId);
}
