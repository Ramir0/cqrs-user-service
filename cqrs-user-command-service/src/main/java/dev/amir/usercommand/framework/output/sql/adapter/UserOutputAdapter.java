package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.Status;
import dev.amir.usercommand.domain.valueobject.user.UserId;
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

        log.info("User with ID: {} and firstName: {} has been successfully saved", savedUser.getId(),
                savedUser.getFirstName());
        return savedUser;
    }

    @Override
    public User update(User user) {
        log.info("Updating user");
        UserId userId = user.getId();
        Optional<UserJpa> existingUser = jpaRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        log.info("Updating user with ID: {}", userId);

        userJpa.setUsername(user.getUsername());
        userJpa.setFirstName(user.getFirstName());
        userJpa.setLastname(user.getLastname());
        userJpa.setEmail(user.getEmail());
        userJpa.setStatus(user.getStatus());
        userJpa.setGender(user.getGender());

        jpaRepository.save(userJpa);
        log.info(
                "User with ID: {} and firstName: {} has been successfully updated",
                userId,
                user.getFirstName()
        );
        return jpaMapper.convert(userJpa);
    }

    @Override
    public void delete(UserId userId) {
        log.info("Deleting user");
        Optional<UserJpa> existingUser = jpaRepository.findById(userId);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        userJpa.setUsername(null);
        userJpa.setEmail(null);
        userJpa.setStatus(Status.REMOVED);

        jpaRepository.save(userJpa);
    }

    @Override
    public boolean existByEmail(User user) {
        log.info("checking if email exists");
        return jpaRepository.existsByEmail(user.getEmail());
    }

    @Override
    public boolean existsByUsername(User user) {
        log.info("checking if user firstName exists");
        return jpaRepository.existsByUsername(user.getUsername());
    }

    @Override
    public boolean isUserRemoved(UserId userId) {
        log.info("checking if user status is removed");
        return jpaRepository.existsByStatusAndId(Status.REMOVED, userId);
    }

    @Override
    public void changePassword(UserId userId, Password password) {
        log.info("Changing password");
        Optional<UserJpa> existingUser = jpaRepository.findById(userId);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(userId.getValue());
        }

        UserJpa userJpa = existingUser.get();
        userJpa.setStatus(Status.PENDING);
        userJpa.setPassword(password);

        jpaRepository.save(userJpa);
    }

    @Override
    public void updateProfile(User user) {
        log.info("Updating User");
        Optional<UserJpa> existingUser = jpaRepository.findById(user.getId());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(user.getId().getValue());
        }

        UserJpa userJpa = existingUser.get();

        userJpa.setFirstName(user.getFirstName());
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
