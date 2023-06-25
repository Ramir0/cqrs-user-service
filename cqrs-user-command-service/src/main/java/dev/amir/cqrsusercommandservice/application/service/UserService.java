package dev.amir.cqrsusercommandservice.application.service;

import dev.amir.cqrsusercommandservice.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String createUser(User user);
}
