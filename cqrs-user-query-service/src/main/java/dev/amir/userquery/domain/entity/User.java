package dev.amir.userquery.domain.entity;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private UserStatus status;
    private String username;
    private UserGender gender;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private Role role;
}
