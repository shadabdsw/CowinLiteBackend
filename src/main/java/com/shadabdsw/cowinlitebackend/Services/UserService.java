package com.shadabdsw.cowinlitebackend.Services;

import java.util.Optional;

import com.shadabdsw.cowinlitebackend.Model.User;
import com.shadabdsw.cowinlitebackend.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<String> getAllPhoneNumbers() {
        return userRepository.findAll().stream().map(User::getPhoneNumber).collect(java.util.stream.Collectors.toList());
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<User> getUserById(String _id) {
        return userRepository.findById(_id);
    }

    public boolean login(User user) {
        if (mongoTemplate.exists(Query.query(Criteria.where("phoneNumber").is(user.getPhoneNumber())), User.class)
            && mongoTemplate.exists(Query.query(Criteria.where("password").is(user.getPassword())), User.class)) {
                return true;
        }
        return false;
    }

    public User updateUser(User user) {
        Optional<User> u = getUserById(user.get_id());
        u.get().setMember(user.getMember());
        return userRepository.save(u.get());
    }

    public void deleteUserById(String _id) {
        userRepository.deleteById(_id);
    }

    public void deleteUserByPhoneNumber(String phoneNumber) {
        userRepository.deleteByPhoneNumber(phoneNumber);
    }

    public User updateUserById(String _id, User user) {
        Optional<User> u = getUserById(_id);
        u.get().setName(user.getName());
        u.get().setPhoneNumber(user.getPhoneNumber());
        u.get().setPassword(user.getPassword());
        u.get().setUserType(user.getUserType());
        u.get().setMember(user.getMember());
        return userRepository.save(u.get());
        
    }

}