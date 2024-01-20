package com.khaiminh.rimer.Model;

public class DriverResponse {
    private boolean accepted;
    private String message; // Optional, for additional message from the backend

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}