package dev.amir.userquery.domain.entity;

import dev.amir.userquery.domain.valueobject.UserStatus;
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
}
