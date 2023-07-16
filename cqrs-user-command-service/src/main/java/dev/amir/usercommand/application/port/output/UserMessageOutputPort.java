package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.User;

public interface UserMessageOutputPort {
    void sendMessage(User user);
}
