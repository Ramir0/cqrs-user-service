package dev.amir.usercommand.framework.output.sql.repository;

import dev.amir.usercommand.domain.valueobject.user.UserEmail;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.UserStatus;
import dev.amir.usercommand.domain.valueobject.user.UserUsername;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpa, UserId> {

    boolean existsByEmail(UserEmail email);

    boolean existsByUsername(UserUsername userName);

    boolean existsByStatusAndId(UserStatus status, UserId userId);
}
