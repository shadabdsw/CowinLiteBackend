package com.shadabdsw.cowinlitebackend.Model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CowinUsers")
public class User {

    private String _id;
    private String name;
    private String phoneNumber;
    private String password;
    private String userType;
    private List<Member> member;

    public User() {
    }

    public User(String _id, String name, String phoneNumber, String password, String userType, List<Member> member) {
        this._id = _id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.member = member;
    }

    public String get_id() {
        return _id;
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

    public List<Member> getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password=" + password
                + ", userType=" + userType + ", member=" + member + "]";
    }
    
}
