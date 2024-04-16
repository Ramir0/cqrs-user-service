package dev.amir.usercommand.framework.output.sql.adapter;

import dev.amir.usercommand.application.port.output.RoleOutputPort;
import dev.amir.usercommand.domain.entity.Role;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.framework.output.sql.entity.RoleJpa;
import dev.amir.usercommand.framework.output.sql.mapper.RoleJpaMapper;
import dev.amir.usercommand.framework.output.sql.repository.RoleJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RoleOutputAdapter implements RoleOutputPort {

    private final RoleJpaRepository jpaRepository;
    private final RoleJpaMapper jpaMapper;

    @Override
    public Role getRole(RoleId roleId) {
        Optional<RoleJpa> roleJpa = jpaRepository.findById(roleId);
        if (roleJpa.isEmpty()) {
            throw new UserNotFoundException(roleId.getValue());
        }
        return jpaMapper.convert(roleJpa.get());
    }
}
