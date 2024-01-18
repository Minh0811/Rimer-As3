package com.khaiminh.rimer.Model;

import java.io.Serializable;

public class Booking implements Serializable {
    private String userId;
    private String driverId;
    private String status;
    private double distance;
    private double price;
    private String startPoint;
    private String endPoint;
    private String _id;

    public Booking(String userId, String driverId, String status, double distance, double price, String startPoint, String endPoint, String _id) {
        this.userId = userId;
        this.driverId = driverId;
        this.status = status;
        this.distance = distance;
        this.price = price;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
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
    public String getId() {
        return _id;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
