package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@RequiredArgsConstructor
@Repository
public class UserOutputAdapter implements UserOutputPort {

    private final UserJpaRepository jpaRepository;
    private final UserJpaMapper jpaMapper;

    @Override
    public User save(User user) {
        log.info("Saving user...");
        var userJpa = jpaMapper.convert(user);
        User savedUser = jpaMapper.convert(jpaRepository.save(userJpa));

        log.info("User with ID: {} and name: {} has been successfully saved", savedUser.getId(), savedUser.getName());
        return savedUser;
    }

    @Override
    public boolean delete(UserId userId) {
        boolean isUserPresent = jpaRepository.existsById(userId.toString());

        if (isUserPresent) {
            jpaRepository.deleteById(userId.toString());
            log.info("Deleting user");
            return true;
        } else {
            log.warn("User not found, deletion skipped.");
            return false;
        }
    }
}
