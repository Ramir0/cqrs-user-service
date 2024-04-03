package dev.amir.userquery.framework.output.sql.entity;

import dev.amir.userquery.domain.entity.Role;
import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserJpa {
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
    private String username;
    @Column
    private UserGender gender;
    @Column
    private LocalDate birthDate;
    @Column
    private LocalDateTime createdAt;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;
}
