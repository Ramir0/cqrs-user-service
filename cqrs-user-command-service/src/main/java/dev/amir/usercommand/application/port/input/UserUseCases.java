package dev.amir.usercommand.application.port.input;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class UserUseCases implements UserInputPort {

    private final UserOutputPort userOutputPort;

    @Override
    public String createUser(User user) {
        if (StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must be empty");
        }

        return userOutputPort.save(user).getId();
    }

    @Override
    public void updateUser(User user) {
        if (!StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must exist");
        }

        userOutputPort.save(user);
    }

    @Override
    public boolean deleteUser(String userId) {
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("Invalid User, id field must exist");
        }

        return userOutputPort.delete(userId);
    }
}
