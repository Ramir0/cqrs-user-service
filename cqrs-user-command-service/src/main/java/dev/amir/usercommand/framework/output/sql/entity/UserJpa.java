package dev.amir.usercommand.framework.output.sql.entity;

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
    private Username username;
    @Convert(converter = UserPasswordConverter.class)
    private Password password;
    @Convert(converter = UserNameConverter.class)
    @Column(name = "name")
    private FirstName firstname;
    @Convert(converter = UserLastNameConverter.class)
    private LastName lastname;
    @Convert(converter = UserEmailConverter.class)
    private Email email;
    @Column
    private Status status;
    @Column
    private Gender gender;
    @Convert(converter = UserBirthDateConverter.class)
    private BirthDate birthDate;
    @Column
    private LocalDateTime createdAt;
}
