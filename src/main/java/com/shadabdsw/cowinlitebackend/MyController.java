package com.shadabdsw.cowinlitebackend;

import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        if(users != null) {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getUserById/{_id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("_id") String _id) {
        Optional<User> user = userService.getUserById(_id);
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getUserByPhoneNumber/{phoneNumber}")
    public ResponseEntity<Optional<User>> getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        Optional<User> user = userService.getUserByPhoneNumber(phoneNumber);
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getAllPhoneNumbers")
    public Iterable<String> getAllPhoneNumbers() {
        return userService.getAllPhoneNumbers();
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            if (HttpStatus.NOT_FOUND.equals(getUserByPhoneNumber(user.getPhoneNumber()).getStatusCode())) {
                User newUser = userService.saveUser(user);
                if (Objects.nonNull(newUser)) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
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
        User u = getUserByPhoneNumber(phoneNumber).getBody().get();
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