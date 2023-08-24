package dev.amir.userquery.framework.output.adapter;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.entity.UserMongo;
import dev.amir.userquery.framework.output.mapper.UserMongoMapper;
import dev.amir.userquery.framework.output.repository.UserMongoRepository;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
=======
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
>>>>>>> 871c25d99146a1d89ffc1d65d6f13762fb9b53c6

@Slf4j
@RequiredArgsConstructor
@Service
public class UserOutputAdapter implements UserOutputPort {
    private final UserMongoRepository userMongoRepository;
    private final UserMongoMapper userMongoMapper;

    @Override
    public Collection<User> getAll() {
        log.info("Fetching all users from the database.");
        return userMongoRepository.findAll()
            .stream()
            .map(userMongoMapper::convert)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String userId) {
        log.info("Fetching user details for userId.");
        return userMongoRepository.findById(userId).map(userMongoMapper::convert);
    }

    @Override
    public User save(User user) {
        log.info("Saving user details for userId.");
        UserMongo savedUser = userMongoRepository.save(userMongoMapper.convert(user));
        return userMongoMapper.convert(savedUser);
    }
}
