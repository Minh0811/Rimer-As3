package com.khaiminh.rimer.Controllers.BookingControllers;

import android.util.Log;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;

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
    public void createNewBooking(String userId, String driverId, String status, double distance, double price, String startPoint, String endPoint) {
        retrofitHandle();

        HashMap<String, Object> map = new HashMap<>();
        map.put("user", userId);
        map.put("driver", driverId);
        map.put("status", "Appending...");
        map.put("distance", distance);
        map.put("price", price);
        map.put("startPoint", startPoint);
        map.put("endPoint", endPoint);

        Call<Void> call = retrofitInterface.createNewBooking(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Create booking", "Create successfully." + response.message());
                } else {
                    Log.e("Create booking", "Failed to create. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network failure or other errors
                Log.e("Create booking", "Error create booking: " + t.getMessage());
            }
        });
    }
}
