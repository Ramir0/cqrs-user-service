package dev.amir.userquery.framework.output.sql.repository;

import dev.amir.userquery.framework.output.sql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}

