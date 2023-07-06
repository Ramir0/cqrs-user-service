package dev.amir.cqrsusercommandservice.application.port.output;

import dev.amir.cqrsusercommandservice.domain.entity.User;

public interface UserOutputPort {
    User save(User user);
    boolean delete(String userId);
}
