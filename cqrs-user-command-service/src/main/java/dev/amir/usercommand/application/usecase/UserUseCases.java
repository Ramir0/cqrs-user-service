package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.UserPassword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {

    private final UserOutputPort userOutputPort;
    private final RetryExecutor retryExecutor;

    @Override
    public UserId createUser(User user) {
        if (userOutputPort.existByEmail(user)) {
            throw new DuplicateUserException(user.getEmail());
        } else if (userOutputPort.existsByUsername(user)) {
            throw new DuplicateUserException(user.getUsername());
        }
        log.info("Generating ID value for user creation");
        user.setId(new UserId());
        user.setRoleId(new RoleId("7ea89508-906f-11ee-b9d1-0242ac120002"));
        user.setCreatedAt(LocalDateTime.now());
        log.info("Attempting to create user with ID: {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.save(user));
        log.info("User with ID: {} successfully created", savedUser.getId());
        return savedUser.getId();
    }

    @Override
    public void updateUser(User user) {
        log.info("Attempting to update user with ID: {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.update(user));
        log.info("User with ID: {} successfully updated", savedUser.getId());
    }

    @Override
    public void deleteUser(UserId userId) {
        log.info("Attempting to delete user with ID: {}", userId);
        retryExecutor.execute(() -> userOutputPort.delete(userId));
        log.info("User with ID: {} successfully deleted", userId);
    }

    @Override
    public void changeUserPassword(UserId userId, UserPassword password) {
        if (userOutputPort.isUserRemoved(userId)) {
            throw new UserNotFoundException(userId.getValue());
        }
        log.info("Attempting to change password with ID: {}", userId);
        retryExecutor.execute(() -> userOutputPort.changePassword(userId, password));
        log.info("User password with ID: {} successfully changed", userId);
    }

    @Override
    public void updateUserProfile(User user) {
        log.info("Attempting to update user with ID: {}", user.getId());
        retryExecutor.execute(() -> userOutputPort.updateProfile(user));
        log.info("User with ID: {} successfully updated", user.getId());
    }
}
