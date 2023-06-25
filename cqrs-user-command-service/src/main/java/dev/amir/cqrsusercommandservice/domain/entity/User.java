package dev.amir.cqrsusercommandservice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private boolean isActive;
}
