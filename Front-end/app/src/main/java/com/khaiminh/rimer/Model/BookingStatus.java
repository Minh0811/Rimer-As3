package com.khaiminh.rimer.Model;

public class BookingStatus {
    private boolean completed;
    private String message; // Optional, for additional message from the backend

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
