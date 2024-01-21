package com.khaiminh.rimer.Controllers.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitControllers {
    public Retrofit retrofit;
    public RetrofitInterface retrofitInterface;
    public String BASE_URL = "https://rimerapp-4cd08109739f.herokuapp.com/";

    public void retrofitController(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }
}
