package com.shadabdsw.cowinlitebackend;

import java.util.Optional;

import com.shadabdsw.cowinlitebackend.Model.User;
import com.shadabdsw.cowinlitebackend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            if (userService.getUserByPhoneNumber(user.getPhoneNumber()) == null) {
                User u = userService.saveUser(user);
                if (u != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(u);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password) {
        User u = getUserByPhoneNumber(phoneNumber);
        try {
            if (u != null) {
                if (u.getPassword().equals(password)) {
                    return ResponseEntity.status(HttpStatus.OK).body(u);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                } 
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();
        }
        
        
    }

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

    

}