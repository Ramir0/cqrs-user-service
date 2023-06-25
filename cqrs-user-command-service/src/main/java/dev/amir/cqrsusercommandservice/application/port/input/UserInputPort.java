package dev.amir.cqrsusercommandservice.application.port.input;

import dev.amir.cqrsusercommandservice.domain.entity.User;

public interface UserInputPort {
    String createUser(User user);
}
