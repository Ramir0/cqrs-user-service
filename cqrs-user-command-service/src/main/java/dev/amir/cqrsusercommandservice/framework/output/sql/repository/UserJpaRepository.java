package dev.amir.cqrsusercommandservice.framework.output.sql.repository;

import dev.amir.cqrsusercommandservice.framework.output.sql.entity.UserJpa;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpa, String> {
}
