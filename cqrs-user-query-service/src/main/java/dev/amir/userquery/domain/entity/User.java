package dev.amir.userquery.domain.entity;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
