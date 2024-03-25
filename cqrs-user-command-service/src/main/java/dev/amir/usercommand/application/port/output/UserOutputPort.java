package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.UserId;

public interface UserOutputPort {
    User save(User user);

    User update(User user);

    void delete(UserId userId);

    boolean existByEmail(User user);

    boolean existsByUsername(User user);

    void changePassword(UserId userId, Password password);

    boolean isUserRemoved(UserId userId);

    void updateProfile(User user);
}
