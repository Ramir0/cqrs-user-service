package dev.amir.usercommand.framework.output.sql.entity;

import dev.amir.usercommand.domain.valueobject.RoleId;
import dev.amir.usercommand.domain.valueobject.UserBirthDate;
import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import dev.amir.usercommand.framework.output.sql.converter.RoleIdConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserBirthDateConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserEmailConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserLastNameConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserNameConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserPasswordConverter;
import dev.amir.usercommand.framework.output.sql.converter.UserUsernameConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa {
    @Id
    private UserId id;
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Convert(converter = RoleIdConverter.class)
    private RoleId roleId;
    @Convert(converter = UserUsernameConverter.class)
    private UserUsername username;
    @Convert(converter = UserPasswordConverter.class)
    private UserPassword password;
    @Convert(converter = UserNameConverter.class)
    private UserName name;
    @Convert(converter = UserLastNameConverter.class)
    private UserLastName lastname;
    @Convert(converter = UserEmailConverter.class)
    private UserEmail email;
    @Column
    private UserStatus status;
    @Column
    private UserGender gender;
    @Convert(converter = UserBirthDateConverter.class)
    private UserBirthDate birthDate;
    @Column
    private LocalDateTime createdAt;
}
