package dev.amir.userquery.framework.output.nosql.entity;

import dev.amir.userquery.domain.valueobject.UserGender;
import dev.amir.userquery.domain.valueobject.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document(collection = "users")
public class UserMongo {
    @MongoId
    private String id;
    private String name;
    private String lastname;
    private String email;
    private UserStatus status;
    private UserGender gender;
}
