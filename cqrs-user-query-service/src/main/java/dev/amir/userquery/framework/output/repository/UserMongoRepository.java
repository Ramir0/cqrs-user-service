package dev.amir.userquery.framework.output.repository;

import dev.amir.userquery.framework.output.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
}
