package dev.amir.cqrsusercommandservice.application.repository;

import dev.amir.cqrsusercommandservice.domain.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    String save(User user);
}
