package dev.amir.userquery.framework.output.sql.entity;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private UserStatus status;
    @Column
    private UserGender gender;
}
