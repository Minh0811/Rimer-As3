package com.khaiminh.rimer.Model;

public class BookingStatus {
    private boolean completed;
    private String message; // Optional, for additional message from the backend

    public boolean isAccepted() {
        return completed;
    }

    public void setAccepted(boolean completed) {
        this.completed = completed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
