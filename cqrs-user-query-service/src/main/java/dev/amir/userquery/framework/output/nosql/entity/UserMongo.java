package dev.amir.userquery.framework.output.nosql.entity;

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
    private boolean isActive;
}
