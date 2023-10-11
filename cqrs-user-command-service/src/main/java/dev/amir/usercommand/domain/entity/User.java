package dev.amir.usercommand.domain.entity;

import dev.amir.usercommand.domain.valueobject.UserId;
import javax.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @Valid
    private UserId id;
    private String name;
    private String lastname;
    private String email;
    private boolean isActive;
}
