package dev.amir.usercommand.domain.entity;

import dev.amir.usercommand.domain.valueobject.permission.Name;
import dev.amir.usercommand.domain.valueobject.permission.PermissionId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Permission {
    private PermissionId id;
    private Name name;
}
