package dev.amir.userquery.framework.output.sql.repository;

import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, String> {
}

