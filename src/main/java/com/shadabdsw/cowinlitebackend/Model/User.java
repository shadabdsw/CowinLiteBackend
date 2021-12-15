package com.shadabdsw.cowinlitebackend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CowinUsers")
public class User {

    private String name;
    private String phoneNumber;
    private String password;
    private String userType;

    public User(String name, String phoneNumber, String password, String userType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", phoneNumber=" + phoneNumber + ", password=" + password + "User Type: " + userType + "]";
    }
    
}
