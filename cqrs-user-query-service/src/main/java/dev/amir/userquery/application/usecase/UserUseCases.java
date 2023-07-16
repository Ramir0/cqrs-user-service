package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.input.UserInputPort;
import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {
    private final UserOutputPort userOutputPort;

    @Override
    public Collection<User> getAllUsers() {
        return userOutputPort.getAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userOutputPort.getById(userId);
    }
}
