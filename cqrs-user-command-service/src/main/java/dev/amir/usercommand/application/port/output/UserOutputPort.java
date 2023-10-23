package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;

public interface UserOutputPort {
    User save(User user);

    User update(User user);

    void delete(UserId userId);
}
