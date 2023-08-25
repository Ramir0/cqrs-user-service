package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserOutputAdapter implements UserOutputPort {

    private final UserJpaRepository jpaRepository;
    private final UserJpaMapper jpaMapper;

    @Override
    public User save(User user) {
        log.info("Saving user...");
        User savedUSer = jpaMapper.convert(jpaRepository.save(jpaMapper.convert(user)));

        log.info("User: {} Saved",savedUSer.getName());
        return savedUSer;
    }

    @Override
    public boolean delete(String userId) {
        Optional<UserJpa> userToDelete = jpaRepository.findById(userId);

        if (userToDelete.isPresent()) {
            jpaRepository.deleteById(userId);
            log.info("User deleted with ID: {}", userId);
            return true;
        } else {
            log.warn("User not found, deletion skipped.");
            return false;
        }
    }
}
