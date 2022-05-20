package com.example.userqueryapi.repository;

import com.example.usercore.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String > {
}
