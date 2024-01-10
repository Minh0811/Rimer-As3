package com.khaiminh.rimer.Controllers.LoginController;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khaiminh.rimer.LoginView.LoginActivity;
import com.khaiminh.rimer.MainActivity;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.RetrofitInterface;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userControllers implements IUserControllers{
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    public void login(String email, String password, Context context){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        Call<User> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 200) {
                    User result = response.body();
                    String name = result.getName();
                    String email = result.getEmail();
                    String password = result.getPassword();

                    User user = new User(name, email, password);

                    LoginActivity loginActivity = new LoginActivity();
                    loginActivity.OnLoginSuccess();

                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void authGoogle(){

    }
}
