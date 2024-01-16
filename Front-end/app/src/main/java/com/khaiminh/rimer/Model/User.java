package com.khaiminh.rimer.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String userType;
    private String _id;

    public User(String name, String email, String password, String userType, String _id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this._id = _id;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUserType() {
        return userType;
    }
    public String getId() {
        return _id;
    }
}

