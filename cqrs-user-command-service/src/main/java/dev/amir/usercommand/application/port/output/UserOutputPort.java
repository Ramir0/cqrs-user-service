package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;

public interface UserOutputPort {
    User save(User user);

    User update(User user);

    void delete(UserId userId);

    boolean existByEmail(User user);

    boolean existsByUsername(User user);

    void changePassword(UserId userId, UserPassword password);

    boolean isUserRemoved(UserId userId);

    void updateProfile(User user);
}
