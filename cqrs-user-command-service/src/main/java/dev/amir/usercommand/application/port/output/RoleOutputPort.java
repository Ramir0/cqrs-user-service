package dev.amir.usercommand.application.port.output;

import dev.amir.usercommand.domain.entity.Role;
import dev.amir.usercommand.domain.valueobject.role.RoleId;

public interface RoleOutputPort {
    Role getRole(RoleId userId);
}
