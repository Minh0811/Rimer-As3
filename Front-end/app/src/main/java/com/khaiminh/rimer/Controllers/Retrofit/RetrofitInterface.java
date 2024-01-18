package com.khaiminh.rimer.Controllers.Retrofit;

import com.khaiminh.rimer.Model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Call<Void> createNewBooking(@Body HashMap<String, Object> map);
    @GET("api/auth/getDrivers")
    Call<List<User>> executeListDrivers();
}
