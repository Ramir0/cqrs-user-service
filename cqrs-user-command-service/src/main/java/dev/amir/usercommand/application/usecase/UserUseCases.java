package dev.amir.usercommand.application.usecase;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {

    private final UserOutputPort userOutputPort;
    private final UserMessageOutputPort userMessageOutputPort;

    @Override
    public String createUser(User user) {
        log.info("Verifying if the 'id' field is empty for user creation.");
        if (StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must be empty");
        }

        User savedUser = userOutputPort.save(user);
        log.info("User successfully created and saved with ID: {}", savedUser.getId());
        userMessageOutputPort.sendMessage(savedUser);
        return savedUser.getId();
    }

    @Override
    public void updateUser(User user) {
        log.info("Verifying if the 'id' field exists for user update.");
        if (!StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must exist");
        }

        User savedUser = userOutputPort.save(user);
        log.info("User successfully updated with ID: {}", savedUser.getId());
        userMessageOutputPort.sendMessage(savedUser);
    }

    @Override
    public boolean deleteUser(String userId) {
        log.info("Attempting to delete user with ID: {}", userId);
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("Invalid User, id field must exist");
        }
        log.info("User successfully deleted with ID: {}", userId);
        return userOutputPort.delete(userId);
    }
}
