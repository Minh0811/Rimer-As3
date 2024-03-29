package com.khaiminh.rimer.Controllers.Retrofit;

import com.khaiminh.rimer.Model.BookingStatus;
import com.khaiminh.rimer.Model.DriverResponse;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.Model.Booking;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @POST("api/auth/login/")
    Call<User> executeLogin(@Body HashMap<String, String> map);
    @POST("api/auth/register/user")
    Call<Void> executeUserSignup(@Body HashMap<String, String> map);
    @POST("api/auth/register/driver")
    Call<Void> executeDriverSignup(@Body HashMap<String, String> map);
    @POST("api/reviews/create-review")
    Call<Void> submitReview(@Body HashMap<String, Object> reviewDetails);
    @POST("api/booking/create")
    Call<Booking> createNewBooking(@Body HashMap<String, Object> map);
    @GET("api/auth/getDrivers")
    Call<List<User>> executeListDrivers();
    @GET("api/users/details/{userId}")
    Call<User> getUserDetails(@Path("userId") String userId);
    // Method to fetch bookings for a specific driver
    @GET("api/booking/driver/{driverId}")
    Call<List<Booking>> getDriverBookings(@Path("driverId") String driverId);
    @GET("api/booking/{bookingId}")
    Call<Booking> getBookingDetails(@Path("bookingId") String bookingId);

    // Method to update booking status
    @PUT("api/booking/{bookingId}")
    Call<Void> updateBookingStatus(@Path("bookingId") String bookingId, @Body HashMap<String, String> map);

    // Method to delete a booking
    @DELETE("api/booking/{bookingId}")
    Call<Void> deleteBooking(@Path("bookingId") String bookingId);

    @POST("/updateName")
    Call<ResponseBody> updateName(@Body RequestBody body);

    @POST("/updateEmail")
    Call<ResponseBody> updateEmail(@Body RequestBody body);

    @POST("/updatePassword")
    Call<ResponseBody> updatePassword(@Body RequestBody body);

    @POST("/api/users/update-username") // Ensure this matches the endpoint defined in your Node.js server
    Call<ResponseBody> updateUsername(@Body HashMap<String, String> map);

    @GET("api/booking/check-response/{bookingId}")
    Call<DriverResponse> checkDriverResponse(@Path("bookingId") String bookingId);

    @GET("api/booking/check-booking-status/{bookingId}")
    Call<BookingStatus> checkBookingStatus(@Path("bookingId") String bookingId);
}
