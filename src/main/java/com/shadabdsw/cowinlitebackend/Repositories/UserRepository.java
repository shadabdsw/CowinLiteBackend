package com.shadabdsw.cowinlitebackend.Repositories;

import java.util.Optional;

import com.shadabdsw.cowinlitebackend.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name);

    User findByPhoneNumber(String phoneNumber);

    Optional<User> findById(String id);


    
}
