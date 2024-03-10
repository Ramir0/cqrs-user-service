package dev.amir.usercommand.domain.entity;

import dev.amir.usercommand.domain.valueobject.role.RoleId;
import dev.amir.usercommand.domain.valueobject.user.UserBirthDate;
import dev.amir.usercommand.domain.valueobject.user.UserEmail;
import dev.amir.usercommand.domain.valueobject.user.UserGender;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.UserLastName;
import dev.amir.usercommand.domain.valueobject.user.UserName;
import dev.amir.usercommand.domain.valueobject.user.UserPassword;
import dev.amir.usercommand.domain.valueobject.user.UserStatus;
import dev.amir.usercommand.domain.valueobject.user.UserUsername;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private UserId id;
    private RoleId roleId;
    private UserUsername username;
    private UserPassword password;
    private UserName name;
    private UserLastName lastname;
    private UserEmail email;
    private UserStatus status;
    private UserGender gender;
    private UserBirthDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
