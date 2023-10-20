package dev.amir.usercommand.framework.output.sql.repository;

import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpa, UUID> {
}
