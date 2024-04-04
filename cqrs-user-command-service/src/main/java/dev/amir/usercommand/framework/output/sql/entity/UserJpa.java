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
import dev.amir.usercommand.framework.output.sql.type.RoleIdType;
import dev.amir.usercommand.framework.output.sql.type.UserIdType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa implements Serializable {
    @Id
    @Type(UserIdType.class)
    private UserId id;
    @Type(RoleIdType.class)
    private RoleId roleId;
    private Username username;
    private Password password;
    @Column(name = "name")
    private FirstName firstname;
    private LastName lastname;
    private Email email;
    private Status status;
    private Gender gender;
    private BirthDate birthDate;
    private LocalDateTime createdAt;
}
