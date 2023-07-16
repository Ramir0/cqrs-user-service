package dev.amir.userquery.framework.output.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "users")
public class UserMongo {
    @MongoId
    private String id;
    private String name;
    private String lastname;
    private String email;
    private boolean isActive;
}
