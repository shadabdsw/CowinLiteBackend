package com.shadabdsw.cowinlitebackend.Model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class UserPostReq {

    @ApiModelProperty(required = true)
    private String name;
    private String phoneNumber;
    private String password;
    private String userType;
    private List<Member> member;

    public UserPostReq() {
    }

    public UserPostReq(String name, String phoneNumber, String password, String userType, List<Member> member) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.member = member;
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
        return "UserPostReq [member=" + member + ", name=" + name + ", password=" + password + ", phoneNumber="
                + phoneNumber + ", userType=" + userType + "]";
    }

}
