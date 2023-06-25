package dev.amir.cqrsusercommandservice.application.service;

import dev.amir.cqrsusercommandservice.application.repository.UserRepository;
import dev.amir.cqrsusercommandservice.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(User user) {
        if (StringUtils.hasText(user.getId())) {
            throw new IllegalArgumentException("Invalid User, id field must be empty");
        }

        return userRepository.save(user);
    }
}
