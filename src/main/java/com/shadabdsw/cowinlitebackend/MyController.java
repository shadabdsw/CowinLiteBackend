package com.shadabdsw.cowinlitebackend;

import java.util.Optional;

import com.shadabdsw.cowinlitebackend.Model.User;
import com.shadabdsw.cowinlitebackend.Model.UserPostReq;
import com.shadabdsw.cowinlitebackend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody UserPostReq user) { //return responsebody of <User>
        User u = new User(user.getName(), user.getPhoneNumber(), user.getPassword(), user.getUserType(), user.getMember());
        return userService.saveUser(u);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}