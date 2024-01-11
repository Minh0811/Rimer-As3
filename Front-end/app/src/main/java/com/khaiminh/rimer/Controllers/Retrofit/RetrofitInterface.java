package com.khaiminh.rimer.Controllers.Retrofit;

import com.khaiminh.rimer.Model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("api/auth/login/")
    Call<User> executeLogin(@Body HashMap<String, String> map);
    @POST("api/auth/register/")
    Call<Void> executeSignup(@Body HashMap<String, String> map);
    @POST("api/auth/authGoogle/")
    Call<Void> executeAuthGoogle(@Body HashMap<String, String> map);
}
