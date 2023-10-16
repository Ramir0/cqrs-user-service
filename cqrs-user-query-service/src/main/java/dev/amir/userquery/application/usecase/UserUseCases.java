package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.exception.UserException;
import dev.amir.userquery.domain.exception.UserNotFoundException;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {
    private final UserOutputPort userOutputPort;

    @Override
    public Collection<User> getAllUsers() {
        log.info("Getting all users");
        try {
            Collection<User> users = userOutputPort.getAll();
            log.info("Users quantity: {}", users.size());
            return users;
        } catch (Exception e) {
            throw new UserException("Error while getting all users", e);
        }
    }

    @Override
    public User getUserById(String userId) {
        log.info("Getting user");
        Optional<User> user = userOutputPort.getById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return user.get();
    }
}
