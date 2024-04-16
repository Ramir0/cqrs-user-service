package dev.amir.usercommand.domain.entity;

import dev.amir.usercommand.domain.valueobject.role.Name;
import dev.amir.usercommand.domain.valueobject.role.RoleId;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role {
    private RoleId id;
    private Name name;
    private Set<Permission> permissions;
}
