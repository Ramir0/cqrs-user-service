package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.aop.annotation.OnUserCreation;
import dev.amir.usercommand.application.aop.annotation.OnUserUpdate;
import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.DuplicateUserException;
import dev.amir.usercommand.domain.valueobject.UserId;
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
        log.info("Generating value 'id' value for user creation");
        user.setId(new UserId());
        log.info("Attempting to create user with 'id': {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.save(user));
        log.info("User with ID: {} successfully created", savedUser.getId());
        return savedUser.getId();
    }

    @OnUserUpdate
    @Override
    public void updateUser(User user) {
        log.info("Attempting to update user with 'id': {}", user.getId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.update(user));
        log.info("User with ID: {} successfully updated", savedUser.getId());
    }

    @Override
    public void deleteUser(UUID userIdParam) {
        log.info("Attempting to delete user with 'id': {}", userIdParam);
        UserId userId = new UserId(userIdParam);
        retryExecutor.execute(() -> userOutputPort.delete(userId));
        log.info("User with ID: {} deleted", userId);
    }
}
