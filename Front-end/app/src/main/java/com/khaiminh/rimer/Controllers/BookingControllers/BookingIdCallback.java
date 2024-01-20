package com.khaiminh.rimer.Controllers.BookingControllers;

public interface BookingIdCallback {
    void onResponse(String bookingId);
    void onFailure(Throwable throwable);
}
