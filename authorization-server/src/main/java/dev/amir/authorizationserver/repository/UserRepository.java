package dev.amir.authorizationserver.repository;

import dev.amir.authorizationserver.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
