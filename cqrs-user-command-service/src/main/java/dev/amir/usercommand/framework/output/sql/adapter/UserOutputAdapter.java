package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import java.util.Optional;
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
        log.info("Saving user");
        User savedUser = jpaMapper.convert(jpaRepository.save(jpaMapper.convert(user)));

        log.info("User with ID: {} and name: {} has been successfully saved", savedUser.getId(), savedUser.getName());
        return savedUser;
    }

    @Override
    public User update(User user) {
        log.info("Updating user");
        UserId userId = user.getId();
        Optional<UserJpa> existingUser = jpaRepository.findById(userId.getValue());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        log.info("Updating user with ID: {}", userId);

        userJpa.setUsername(user.getUsername());
        userJpa.setName(user.getName());
        userJpa.setLastname(user.getLastname());
        userJpa.setEmail(user.getEmail());
        userJpa.setStatus(user.getStatus());
        userJpa.setGender(user.getGender());
        userJpa.setUpdatedAt(user.getUpdatedAt());

        jpaRepository.save(userJpa);
        log.info(
                "User with ID: {} and name: {} has been successfully updated",
                userId,
                user.getName()
        );
        return jpaMapper.convert(userJpa);
    }

    @Override
    public void delete(UserId userId) {
        log.info("Deleting user");
        Optional<UserJpa> existingUser = jpaRepository.findById(userId.getValue());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        userJpa.setUsername(null);
        userJpa.setEmail(null);
        userJpa.setStatus(UserStatus.REMOVED);

        jpaRepository.save(userJpa);
    }

    @Override
    public boolean existByEmail(User user) {
        log.info("checking if email exists");
        return jpaRepository.existsByEmail(user.getEmail());
    }

    @Override
    public boolean existsByUsername(User user) {
        log.info("checking if user name exists");
        return jpaRepository.existsByUsername(user.getUsername());
    }

    @Override
    public boolean isUserRemoved(UserId userId) {
        log.info("checking if user status is removed");
        return jpaRepository.existsByStatusAndId(UserStatus.REMOVED, userId.getValue());
    }

    @Override
    public void changePassword(UserId userId, UserPassword password) {
        log.info("Changing password");
        Optional<UserJpa> existingUser = jpaRepository.findById(userId.getValue());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        userJpa.setStatus(UserStatus.PENDING);
        userJpa.setPassword(password);

        jpaRepository.save(userJpa);
    }

    @Override
    public void updateProfile(User user) {
        log.info("Updating User");
        Optional<UserJpa> existingUser = jpaRepository.findById(user.getId().getValue());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(user.getId().getValue());
        }

        UserJpa userJpa = existingUser.get();

        userJpa.setName(user.getName());
        userJpa.setLastname(user.getLastname());
        userJpa.setGender(user.getGender());
        userJpa.setBirthDate(user.getBirthDate());

        jpaRepository.save(userJpa);
        log.info(
                "User with ID: {} has been successfully updated",
                user.getId().getValue()
        );
    }
}
