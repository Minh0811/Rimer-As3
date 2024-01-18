package com.khaiminh.rimer.Controllers.BookingControllers;

public interface IBookingControllers {
    void createNewBooking(String userId, String driverId, String status, double distance, double price, String startPoint, String endPoint);
}
