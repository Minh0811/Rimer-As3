package com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.DriverViews.DriverProfile.DriverProfile;
import com.khaiminh.rimer.Views.UserViews.UserHomeActivity.UserHomeActivity;
import com.khaiminh.rimer.Views.UserViews.UserProfile.UserProfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTripActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListTripAdapter listTripAdapter;
    RetrofitInterface retrofitInterface;

    User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trip);

        Intent intent = getIntent();
        String userId = null;
        if (intent.getExtras() != null) {
            userId = intent.getStringExtra("userId");
            currUser = (User) intent.getSerializableExtra("currUser");
            Log.d("ListTripActivity", "User ID: " + userId);
        }

        recyclerView = findViewById(R.id.listAvailableDrivers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        List<User> tripList = getTripData();
//
//        listTripAdapter = new ListTripAdapter(tripList);
//        recyclerView.setAdapter(listTripAdapter);

        ImageButton profileButton = (ImageButton) findViewById(R.id.driverBtn);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTripActivity.this, DriverProfile.class);
                intent.putExtra("curUser", currUser);
                startActivityForResult(intent, 100);
            }
        });

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
                    List<Booking> allBookings = response.body();
                    List<Booking> pendingBookings = new ArrayList<>();

                    // Filter bookings with "pending" status
                    for (Booking booking : allBookings) {
                        if ("pending".equalsIgnoreCase(booking.getStatus())) {
                            pendingBookings.add(booking);
                        }
                    }

                    Log.d("ListTripActivity", "Number of pending bookings fetched: " + pendingBookings.size());
                    for (Booking booking : pendingBookings) {
                        Log.d("ListTripActivity", "Booking: " + booking.toString());
                    }

                    // Use the filtered list for the adapter
                    listTripAdapter = new ListTripAdapter(pendingBookings);
                    recyclerView.setAdapter(listTripAdapter);
                } else {
                    Log.d("ListTripActivity", "Response not successful or empty body! Response code: " + response.code());
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
                        Log.d("ListTripActivity", "Error body: " + errorBody);
                    } catch (IOException e) {
                        Log.e("ListTripActivity", "Error reading error body: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                Log.e("ListTripActivity", "Error fetching bookings: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Fetch bookings for the driver again to refresh the list
        Intent intent = getIntent();
        String userId = null;
        if (intent.getExtras() != null) {
            userId = intent.getStringExtra("userId");
        }
        fetchBookingsForDriver(userId);
    }
}