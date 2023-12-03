package dev.amir.userquery.util;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import dev.amir.userquery.framework.output.sql.entity.UserJpa;

public final class DefaultObject {
    public static final String defaultUserId = "b6a3d07d-0c0d-4d7f-b968-453808256e31";
    public static final String defaultUserId2 = "ad88345e-3abd-4fbd-81eb-c6ba9ff17c6f";
    public static final UserJpa defaultUserJpa = new UserJpa(
            defaultUserId,
            "DefaultName",
            "DefaultLastname",
            "default@email.com",
            UserStatus.ACTIVE,
            UserGender.MALE
    );
    public static final UserJpa defaultUserJpa2 = new UserJpa(
            defaultUserId2,
            "DefaultName2",
            "DefaultLastname2",
            "default@email2.com",
            UserStatus.INACTIVE,
            UserGender.NON_BINARY
    );

    private DefaultObject() {
    }
}
