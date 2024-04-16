package dev.amir.usercommand.framework.output.sql.repository;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.framework.output.sql.entity.RoleJpa;
import org.springframework.data.repository.CrudRepository;

public interface RoleJpaRepository extends CrudRepository<RoleJpa, RoleId> {
}
