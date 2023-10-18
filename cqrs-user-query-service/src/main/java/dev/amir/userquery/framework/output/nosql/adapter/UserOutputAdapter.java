package dev.amir.userquery.framework.output.nosql.adapter;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.nosql.entity.UserMongo;
import dev.amir.userquery.framework.output.nosql.mapper.UserMongoMapper;
import dev.amir.userquery.framework.output.nosql.repository.UserMongoRepository;
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
    private final UserMongoRepository userMongoRepository;
    private final UserMongoMapper userMongoMapper;

    @Override
    public Collection<User> getAll() {
        log.info("Getting all users from the database");
        return userMongoRepository.findAll()
                .stream()
                .map(userMongoMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String userId) {
        log.info("Getting user by ID {} from the database", userId);
        return userMongoRepository.findById(userId).map(userMongoMapper::convert);
    }

    @Override
    public void save(User user) {
        log.info("Saving user with ID {} in the database", user.getId());
        UserMongo savedUser = userMongoRepository.save(userMongoMapper.convert(user));
        log.info("User with ID {} saved in the database", savedUser.getId());
    }
}