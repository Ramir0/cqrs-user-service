package dev.amir.cqrsusercommandservice.application.port.input;

import dev.amir.cqrsusercommandservice.application.port.output.UserOutputPort;
import dev.amir.cqrsusercommandservice.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class UserInputAdapter implements UserInputPort {

    private final UserOutputPort userOutputPort;

    @Override
    public String createUser(User user) {
        if (StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must be empty");
        }

        return userOutputPort.save(user).getId();
    }
}
