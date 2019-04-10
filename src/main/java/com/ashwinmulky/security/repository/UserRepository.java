package com.ashwinmulky.security.repository;

import com.ashwinmulky.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByIdAndName(String id, String name);

    Optional<User> findOneByName(String name);

}
