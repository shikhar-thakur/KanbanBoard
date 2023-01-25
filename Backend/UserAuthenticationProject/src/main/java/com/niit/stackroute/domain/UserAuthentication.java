package com.niit.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAuthentication {

    @Id
    private String email;
    private String userName;
    private String password;

    public UserAuthentication() {
    }

    public UserAuthentication(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserAuthentication{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


