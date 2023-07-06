package dev.amir.cqrsusercommandservice.framework.output.sql.adapter;

import dev.amir.cqrsusercommandservice.application.port.output.UserOutputPort;
import dev.amir.cqrsusercommandservice.domain.entity.User;
import dev.amir.cqrsusercommandservice.framework.output.sql.mapper.UserJpaMapper;
import dev.amir.cqrsusercommandservice.framework.output.sql.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserOutputAdapter implements UserOutputPort {

    private final UserJpaRepository jpaRepository;
    private final UserJpaMapper jpaMapper;

    @Override
    public User save(User user) {
        return jpaMapper.convert(jpaRepository.save(jpaMapper.convert(user)));
    }

    @Override
    public boolean delete(String userId) {
        jpaRepository.deleteById(userId);
        return true;
    }
}
