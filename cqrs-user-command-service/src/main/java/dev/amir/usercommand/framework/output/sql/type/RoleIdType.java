package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import java.io.Serializable;

public class RoleIdType extends BaseIdType<RoleId> {
    @Override
    public Class<RoleId> returnedClass() {
        return RoleId.class;
    }

    @Override
    public RoleId assemble(Serializable cached, Object owner) {
        return (RoleId) cached;
    }

    @Override
    RoleId newInstance(String value) {
        return new RoleId(value);
    }
}
