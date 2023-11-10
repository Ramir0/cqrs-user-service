package dev.amir.usercommand.framework.output.sql.entity;

import dev.amir.usercommand.domain.valueobject.UserStatus;
import java.util.UUID;
import javax.persistence.Column;
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
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private UserStatus status;
    @Column
    private String username;
    @Column
    private Byte gender;
    // TODO ADD enum class male,famele, noBinari
    @Column
    private java.sql.Date birth_date;
    @Column
    private java.sql.Timestamp created_at;
    @Column
    private java.sql.Timestamp updated_at;
}
