package dev.amir.usercommand.framework.output.sql.entity;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserGender;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Type(type = "org.hibernate.type.UUIDCharType")
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
