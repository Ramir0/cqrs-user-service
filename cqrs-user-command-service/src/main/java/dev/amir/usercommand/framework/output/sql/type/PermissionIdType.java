package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.permission.PermissionId;
import java.io.Serializable;

public class PermissionIdType extends BaseIdType<PermissionId> {
    @Override
    PermissionId newInstance(String value) {
        return new PermissionId(value);
    }

    @Override
    public Class<PermissionId> returnedClass() {
        return PermissionId.class;
    }

    @Override
    public PermissionId assemble(Serializable cached, Object owner) {
        return (PermissionId) cached;
    }
}
