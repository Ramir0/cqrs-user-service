package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.executor.RetryExecutor;
import dev.amir.usercommand.domain.entity.User;
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
    private final UserMessageOutputPort userMessageOutputPort;
    private final RetryExecutor retryExecutor;

    @Override
    public UserId createUser(User user) {
        log.info("Verifying if the 'id' field is empty for user creation");
        user.setId(new UserId());
        User savedUser = retryExecutor.execute(() -> userOutputPort.save(user));
        log.info("User with ID: {} successfully created", savedUser.getId());
        retryExecutor.asyncExecute(() -> userMessageOutputPort.sendMessage(savedUser));
        return savedUser.getId();
    }

    @Override
    public void updateUser(User user) {
        log.info("Verifying if the 'id' field exists for user update");
        User savedUser = retryExecutor.execute(() -> userOutputPort.update(user));
        log.info("User with ID: {} successfully updated", savedUser.getId());
        retryExecutor.asyncExecute(() -> userMessageOutputPort.sendMessage(savedUser)) ;
    }

    @Override
    public void deleteUser(UUID userIdParam) {
        log.info("Attempting to delete user with ID: {}", userIdParam);
        UserId userId = new UserId(userIdParam);
        retryExecutor.execute(() -> userOutputPort.delete(userId));
        log.info("User with ID: {} deleted", userId);
    }
}
