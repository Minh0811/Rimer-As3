package com.khaiminh.rimer.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;
    private String userType;
    private String _id;
    private String onGoingBooking;
    private List<Booking> bookingHistory;

    public User(String name, String email, String password, String userType, String _id, String onGoingBooking, List<Booking> bookingHistory) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this._id = _id;
        this.onGoingBooking = onGoingBooking;
        this.bookingHistory = bookingHistory;
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
    public String getOnGoingBooking() {
        return onGoingBooking;
    }
    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }
}

