package dev.amir.usercommand.domain.entity;

import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private UserId id;
    private String name;
    private String lastname;
    private String email;
    private UserStatus status;
    private String username;
    private Byte gender;
    private LocalDate birth_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
