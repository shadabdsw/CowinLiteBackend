package com.shadabdsw.cowinlitebackend;

import java.util.Optional;

import com.shadabdsw.cowinlitebackend.Model.User;
import com.shadabdsw.cowinlitebackend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{_id}")
    public Optional<User> getUserById(@PathVariable("_id") String _id) {
        return userService.getUserById(_id);
    }

    @GetMapping("/getUserByPhoneNumber/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getAllPhoneNumbers")
    public Iterable<String> getAllPhoneNumbers() {
        return userService.getAllPhoneNumbers();
    }

    @GetMapping("/login/{phoneNumber}/{password}")
    public boolean login(@PathVariable("phoneNumber") String phoneNumber, @PathVariable("password") String password) {
        return userService.login(new User(phoneNumber, password));
    }

    @GetMapping("/users/{_id}")
    @ResponseBody
    public ResponseEntity<Object> Get(@PathVariable String _id) {
        try {
            Optional<User> result = userService.Get(_id);
            if (result.isPresent()) {
                return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
            } else {
                return ResponseHandler.generateResponse("Data not found!", HttpStatus.NOT_FOUND, result);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        try {
            User result = userService.saveUser(user);
            if (result != null) {
                return ResponseHandler.generateResponse("User created!", HttpStatus.CREATED, result);
            } else {
                return ResponseHandler.generateResponse("Data not saved!", HttpStatus.NOT_FOUND, result);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // public User saveUser(@RequestBody User user) {
    //     return userService.saveUser(user);
    // }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/deleteUserById/{_id}")
    public void deleteUserById(@PathVariable("_id") String _id) {
        userService.deleteUserById(_id);
    }

    @DeleteMapping("/deleteUserByPhoneNumber/{phoneNumber}")
    public void deleteUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        userService.deleteUserByPhoneNumber(phoneNumber);
    }

    @PutMapping("/update/{_id}")
    public User updateUserById(@PathVariable("_id") String _id, @RequestBody User user) {
        return userService.updateUserById(_id, user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.loginUser(user);
    }

    
}