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

    public User(String name, String phoneNumber, String password, String userType, List<Member> member) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.member = member;
    }

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password=" + password
                + ", userType=" + userType + ", member=" + member + "]";
    }

}
