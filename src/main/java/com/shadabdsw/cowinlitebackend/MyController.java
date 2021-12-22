package com.shadabdsw.cowinlitebackend;

import com.shadabdsw.cowinlitebackend.Model.User;
import com.shadabdsw.cowinlitebackend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class MyController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) { //return responsebody
        return userService.saveUser(user);
    }
    
    @GetMapping("/")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber);
    }

}