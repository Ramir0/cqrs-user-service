package dev.amir.userquery.framework.output.nosql.repository;

import dev.amir.userquery.framework.output.nosql.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
}
