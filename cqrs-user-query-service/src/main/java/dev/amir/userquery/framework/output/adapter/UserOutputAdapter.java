package dev.amir.userquery.framework.output.adapter;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.framework.output.entity.UserMongo;
import dev.amir.userquery.framework.output.mapper.UserMongoMapper;
import dev.amir.userquery.framework.output.repository.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserOutputAdapter implements UserOutputPort {
    private final UserMongoRepository userMongoRepository;
    private final UserMongoMapper userMongoMapper;

    @Override
    public Collection<User> getAll() {
        return userMongoRepository.findAll()
                .stream()
                .map(userMongoMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(String userId) {
        return userMongoRepository.findById(userId).map(userMongoMapper::convert);
    }

    @Override
    public User save(User user) {
        UserMongo savedUser = userMongoRepository.save(userMongoMapper.convert(user));
        return userMongoMapper.convert(savedUser);
    }
}
