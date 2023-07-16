package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.input.UserCommandInputPort;
import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCommandUseCases implements UserCommandInputPort {
    private final UserOutputPort userOutputPort;

    @Override
    public User createUser(User user) {
        return userOutputPort.save(user);
    }
}
