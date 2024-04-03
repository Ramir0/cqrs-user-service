package dev.amir.userquery.framework.input.rest.response;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetUserByIdResponse(String id, String name, String lastname, String email, UserStatus status,
                                  String username, UserGender gender, LocalDate birthDate, LocalDateTime createdAt,
                                  String role) {
}
