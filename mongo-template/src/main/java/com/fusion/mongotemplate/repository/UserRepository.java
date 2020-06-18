package com.fusion.mongotemplate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fusion.mongotemplate.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {

}
