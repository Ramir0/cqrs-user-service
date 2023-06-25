package dev.amir.cqrsusercommandservice.domain.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private boolean isActive;
}
