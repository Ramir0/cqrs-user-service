package dev.amir.userquery.framework.output.sql.adapter;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.sql.mapper.UserEntityMapper;
import dev.amir.userquery.framework.output.sql.repository.UserJpaRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserOutputAdapter implements UserOutputPort {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Collection<User> getAll() {
        log.info("Getting all users from the database");
        return userJpaRepository.findAll()
                .stream()
                .map(userEntityMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String userId) {
        log.info("Getting user by ID {} from the database", userId);
        return userJpaRepository.findById(userId).map(userEntityMapper::convert);
    }
}
