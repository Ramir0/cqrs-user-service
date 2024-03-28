package dev.amir.userquery.util;

import dev.amir.userquery.domain.entity.Role;
import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DefaultObject {
    public static final String defaultUserId = "b6a3d07d-0c0d-4d7f-b968-453808256e31";
    public static final String defaultUserId2 = "ad88345e-3abd-4fbd-81eb-c6ba9ff17c6f";
    public static final LocalDate defaultBirthDate = LocalDate.parse("2000-01-06");
    public static final LocalDateTime defaultCreatedAt = LocalDateTime.parse("2010-10-31T08:30:00");
    public static final UserJpa defaultUserJpa = new UserJpa(
            defaultUserId,
            "DefaultName",
            "DefaultLastname",
            "default@email.com",
            UserStatus.ACTIVE,
            "user123abc",
            UserGender.MALE,
            defaultBirthDate,
            defaultCreatedAt,
            new Role()
    );
    public static final UserJpa defaultUserJpa2 = new UserJpa(
            defaultUserId2,
            "DefaultName2",
            "DefaultLastname2",
            "default@email2.com",
            UserStatus.INACTIVE,
            "user123abc",
            UserGender.NON_BINARY,
            defaultBirthDate,
            defaultCreatedAt,
            new Role()
    );

    private DefaultObject() {
    }
}
