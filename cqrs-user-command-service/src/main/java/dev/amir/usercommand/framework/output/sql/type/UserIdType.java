package dev.amir.usercommand.framework.output.sql.type;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import java.io.Serializable;

public class UserIdType extends BaseIdType<UserId> {
    @Override
    public Class<UserId> returnedClass() {
        return UserId.class;
    }

    @Override
    public UserId assemble(Serializable cached, Object owner) {
        return (UserId) cached;
    }

    @Override
    UserId newInstance(String value) {
        return new UserId(value);
    }
}
