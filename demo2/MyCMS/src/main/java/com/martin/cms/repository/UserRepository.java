package com.martin.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.martin.cms.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {

}
