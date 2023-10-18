package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.input.UserCommandInputPort;
import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCommandUseCases implements UserCommandInputPort {
    private final UserOutputPort userOutputPort;

    @Override
    public void saveUser(User user) {
        log.info("Saving User");
        userOutputPort.save(user);
    }
}
