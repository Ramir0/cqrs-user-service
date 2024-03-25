package dev.amir.usercommand.util;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.BirthDate;
import dev.amir.usercommand.domain.valueobject.user.Email;
import dev.amir.usercommand.domain.valueobject.user.FirstName;
import dev.amir.usercommand.domain.valueobject.user.Gender;
import dev.amir.usercommand.domain.valueobject.user.LastName;
import dev.amir.usercommand.domain.valueobject.user.Password;
import dev.amir.usercommand.domain.valueobject.user.Status;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DefaultObject {

    public static final UserId defaultUserId = new UserId("b6a3d07d-0c0d-4d7f-b968-453808256e31");
    public static final RoleId defaultRoleId = new RoleId("9abef656-906f-11ee-b9d1-0242ac120002");
    public static final Username defaultUsername = new Username("user123abc");
    public static final Password defaultPassword = new Password("New.Password123#");
    public static final Status defaultStatus = Status.ACTIVE;
    public static final FirstName defaultFirstName = new FirstName("FirstName");
    public static final LastName defaultLastName = new LastName("Lastname");
    public static final Email defaultEmail = new Email("user123@example.net");
    public static final BirthDate defaultBirthDate = new BirthDate(LocalDate.parse("2000-01-06"));
    public static final UserJpa defaultUserJpa = new UserJpa(
            defaultUserId,
            defaultRoleId,
            defaultUsername,
            defaultPassword,
            defaultFirstName,
            defaultLastName,
            defaultEmail,
            defaultStatus,
            Gender.MALE,
            defaultBirthDate,
            LocalDateTime.of(2020, 1, 31, 11, 35, 20)
    );

    private DefaultObject() {
    }
}
