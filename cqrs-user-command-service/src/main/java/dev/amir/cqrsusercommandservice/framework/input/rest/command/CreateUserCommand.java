package dev.amir.cqrsusercommandservice.framework.input.rest.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand {
    private String name;
    private String lastname;
    private String email;
    private Boolean isActive;
}
