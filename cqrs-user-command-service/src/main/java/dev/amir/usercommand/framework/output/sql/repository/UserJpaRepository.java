package dev.amir.usercommand.framework.output.sql.repository;

import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.Status;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpa, UserId> {

    boolean existsByEmail(Email email);

    boolean existsByUsername(Username userName);

    boolean existsByStatusAndId(Status status, UserId userId);
}
