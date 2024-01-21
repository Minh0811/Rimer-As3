package com.khaiminh.rimer.Model;

import java.io.Serializable;

public class Booking implements Serializable {
    private String _id;
    private String user; // Changed from userId
    private String driver; // Changed from driverId
    private String status;
    private double distance;
    private double price;
    private String startPoint;
    private String endPoint;

    public Booking(String _id, String user, String driver, String status, double distance, double price, String startPoint, String endPoint) {
        this._id = _id;
        this.user = user; // Changed from userId
        this.driver = driver; // Changed from driverId
        this.status = status;
        this.distance = distance;
        this.price = price;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
    public String getUserId() {
        return user;
    }

    public void setUserId(String user) {
        this.user = user;
    }

    public String getDriverId() {
        return driver;
    }

    public void setDriverId(String driver) {
        this.driver = driver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + _id + '\'' +
                ", userId='" + user + '\'' +
                ", driverId='" + driver + '\'' +
                ", status='" + status + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                // ... include other fields if you want
                '}';
    }
}
