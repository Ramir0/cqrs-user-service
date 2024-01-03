package dev.amir.usercommand.util;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DefaultObject {
    public static final UserId defaultUserId = new UserId("b6a3d07d-0c0d-4d7f-b968-453808256e31");
    public static final RoleId defaultRoleId = new RoleId("9abef656-906f-11ee-b9d1-0242ac120002");
    public static final UserUsername defaultUserUsername = new UserUsername("user123abc");
    public static final UserPassword defaultPassword = new UserPassword("New.Password123#");
    public static final UserStatus defaultUserStatus = UserStatus.ACTIVE;
    public static final UserName defaultUserName = new UserName("UserName");
    public static final UserLastName defaultUserLastName = new UserLastName("UserLastname");
    public static final UserJpa defaultUserJpa = new UserJpa(
            defaultUserId.getValue(),
            defaultRoleId.getValue(),
            defaultUserUsername,
            defaultPassword,
            defaultUserName,
            defaultUserLastName,
            "default@email.com",
            defaultUserStatus,
            UserGender.MALE,
            LocalDate.of(2000, 12, 25),
            LocalDateTime.of(2020, 1, 31, 11, 35, 20),
            LocalDateTime.of(2023, 6, 15, 8, 51, 40)
    );

    private DefaultObject() {
    }
}
