package com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTripActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListTripAdapter listTripAdapter;
    RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        recyclerView = findViewById(R.id.listAvailableDrivers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        List<User> tripList = getTripData();
//
//        listTripAdapter = new ListTripAdapter(tripList);
//        recyclerView.setAdapter(listTripAdapter);

        Intent intent = getIntent();
        String userId = null;
        if (intent.getExtras() != null) {
            userId = intent.getStringExtra("userId");
            Log.d("ListTripActivity", "User ID: " + userId);
        }
        // Initialize Retrofit
        RetrofitControllers retrofitControllers = new RetrofitControllers();
        retrofitControllers.retrofitController();
        retrofitInterface = retrofitControllers.getRetrofitInterface();

        // Fetch bookings for the driver
        fetchBookingsForDriver(userId);

    }
    private void fetchBookingsForDriver(String driverId) {
        Call<List<Booking>> call = retrofitInterface.getDriverBookings(driverId);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Booking> bookingList = response.body();

                    listTripAdapter = new ListTripAdapter(bookingList);
                    recyclerView.setAdapter(listTripAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}