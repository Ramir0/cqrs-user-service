package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.application.retry.RetryExecutor;
import dev.amir.usercommand.application.validation.Validator;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {

    private final UserOutputPort userOutputPort;
    private final UserMessageOutputPort userMessageOutputPort;
    private final RetryExecutor retryExecutor;
    private final Validator validator;

    @Override
    public UserId createUser(User user) {
        user.setId(new UserId());
        validator.validate(user);
        User savedUser = retryExecutor.execute(() -> userOutputPort.save(user));
        retryExecutor.asyncExecute(() -> userMessageOutputPort.sendMessage(savedUser));
        return savedUser.getId();
    }

    @Override
    public void updateUser(User user) {
        validator.validate(user);
        User savedUser = userOutputPort.save(user);
        userMessageOutputPort.sendMessage(savedUser);
    }

    @Override
    public boolean deleteUser(UUID userIdParam) {
        UserId userId = new UserId(userIdParam);
        validator.validate(userId);
        return userOutputPort.delete(userId);
    }
}
