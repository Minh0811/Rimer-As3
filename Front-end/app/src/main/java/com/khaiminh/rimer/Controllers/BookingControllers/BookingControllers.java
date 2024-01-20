package com.khaiminh.rimer.Controllers.BookingControllers;

import android.util.Log;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Controllers.BookingControllers.BookingIdCallback;
import com.khaiminh.rimer.Model.Booking;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingControllers implements IBookingControllers{
    private RetrofitInterface retrofitInterface;
    private RetrofitControllers retrofitControllers = new RetrofitControllers();

    public void retrofitHandle(){
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }

    @Override
    public void createNewBooking(String userId, String driverId, String status, double distance, double price, String startPoint, String endPoint, BookingIdCallback bookingIdCallback) {
        retrofitHandle();

        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userId);
        map.put("driver", driverId);
        map.put("status", "pending");
        map.put("distance", distance);
        map.put("price", price);
        map.put("startPoint", startPoint);
        map.put("endPoint", endPoint);

        Call<Booking> call = retrofitInterface.createNewBooking(map);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Booking booking = response.body();
                    String bookingId = booking.getId(); // Extract the booking ID
                    bookingIdCallback.onResponse(bookingId); // Pass the booking ID to the callback
                } else {
                    Log.e("Create booking", "Failed to create. Response code: " + response.code());
                    bookingIdCallback.onFailure(new Throwable("Failed to create booking. Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                Log.e("Create booking", "Error create booking: " + t.getMessage());
                bookingIdCallback.onFailure(t);
            }
        });
    }

    public void updateBookingStatus(String bookingId, String newStatus, UpdateBookingStatusCallback callback) {
        retrofitHandle();

        HashMap<String, String> map = new HashMap<>();
        map.put("status", newStatus);

        Call<Void> call = retrofitInterface.updateBookingStatus(bookingId, map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Update booking status", "Booking status updated successfully.");
                    callback.onSuccess();
                } else {
                    Log.e("Update booking status", "Failed to update booking. Response code: " + response.code());
                    callback.onFailure("Failed to update booking. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Update booking status", "Error updating booking status: " + t.getMessage());
                callback.onFailure("Error updating booking status: " + t.getMessage());
            }
        });
    }

    public void deleteBooking(String bookingId, DeleteBookingCallback callback) {
        retrofitHandle();

        Call<Void> call = retrofitInterface.deleteBooking(bookingId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Delete booking", "Booking deleted successfully.");
                    callback.onSuccess();
                } else {
                    Log.e("Delete booking", "Failed to delete booking. Response code: " + response.code());
                    callback.onFailure("Failed to delete booking. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Delete booking", "Error deleting booking: " + t.getMessage());
                callback.onFailure("Error deleting booking: " + t.getMessage());
            }
        });
    }
}
