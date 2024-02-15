package dev.amir.usercommand.framework.output.sql.entity;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserGender;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserLastName;
import dev.amir.usercommand.domain.valueobject.UserName;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
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
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    private UserId id; // TODO error en post y update
    @JdbcTypeCode(java.sql.Types.VARCHAR)
    @Column(name = "role_id")
    private UUID roleId;
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
    @Column
    private LocalDate birthDate;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
}
