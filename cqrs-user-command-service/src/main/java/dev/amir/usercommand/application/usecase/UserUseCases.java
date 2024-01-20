package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.aop.annotation.OnUserCreation;
import dev.amir.usercommand.application.aop.annotation.OnUserUpdate;
import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {

    private final UserOutputPort userOutputPort;
    private final RetryExecutor retryExecutor;

    @OnUserCreation
    @Override
    public UserId createUser(User user) {
        if (userOutputPort.existByEmail(user)) {
            throw new DuplicateUserException(user.getEmail());
        } else if (userOutputPort.existsByUsername(user)) {
            throw new DuplicateUserException(user.getUsername());
        }
        log.info("Generating ID value for user creation");
        user.setId(new UserId());
        log.info("Attempting to create user with ID: {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.save(user));
        log.info("User with ID: {} successfully created", savedUser.getId());
        return savedUser.getId();
    }

    @OnUserUpdate
    @Override
    public void updateUser(User user) {
        log.info("Attempting to update user with ID: {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.update(user));
        log.info("User with ID: {} successfully updated", savedUser.getId());
    }

    @Override
    public void deleteUser(UUID userIdParam) {
        log.info("Attempting to delete user with ID: {}", userIdParam);
        UserId userId = new UserId(userIdParam);
        retryExecutor.execute(() -> userOutputPort.delete(userId));
        log.info("User with ID: {} deleted", userId);
    }

    @Override
    public void changeUserPassword(UUID userIdParam, UserPassword password) {
        UserId userId = new UserId(userIdParam);
        if (userOutputPort.isUserRemoved(userId)) {
            throw new UserNotFoundException(userId.getValue());
        }
        log.info("Attempting to change password with ID: {}", userId);
        User user = retryExecutor.execute(() -> userOutputPort.changePassword(userId, password));
        log.info("User password with ID: {} successfully changed", user.getId());
    }
}
