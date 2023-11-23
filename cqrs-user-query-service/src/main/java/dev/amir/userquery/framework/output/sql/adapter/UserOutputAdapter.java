package dev.amir.userquery.framework.output.sql.adapter;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.entity.UserEntity;
import dev.amir.userquery.framework.output.sql.mapper.UserEntityMapper;
import dev.amir.userquery.framework.output.sql.repository.UserRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Primary
@Service
public class UserOutputAdapter implements UserOutputPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Collection<User> getAll() {
        log.info("Getting all users from the database");
        return userRepository.findAll()
                .stream()
                .map(userEntityMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String userId) {
        log.info("Getting user by ID {} from the database", userId);
        return userRepository.findById(userId).map(userEntityMapper::convert);
    }

    @Override
    public void save(User user) {
        log.info("Saving user with ID {} in the database", user.getId());
        UserEntity userEntity = userEntityMapper.convert(user);
        userRepository.save(userEntity);
        log.info("User with ID {} saved in the database", userEntity.getId());
    }
}
