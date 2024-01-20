package com.khaiminh.rimer.Views.UserViews.BookingView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.khaiminh.rimer.Controllers.BookingControllers.BookingControllers;
import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity.TripWaitingConfirmationActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    ArrayList<User> drivers = new ArrayList<>();
    double distance, price;
    String userId, startPoint, endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //start point - end point
        TextInputEditText startPointField = (TextInputEditText) findViewById(R.id.start);
        TextInputEditText endPointField = (TextInputEditText) findViewById(R.id.arrive);

        // Find the back button
        Button backButton = findViewById(R.id.backbtn);

        Button findDriverButton = findViewById(R.id.button_under_map);
        findDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drivers.isEmpty()) {
                    User randomDriver = drivers.get(new Random().nextInt(drivers.size()));
                    createBookingWithRandomDriver(randomDriver);
                } else {
                    Toast.makeText(BookingActivity.this, "No drivers available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener to finish the activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity
                finish();
            }
        });
        Intent intent = getIntent();
        distance = Double.parseDouble(intent.getStringExtra("distance"));
        price = Double.parseDouble(intent.getStringExtra("price"));
        drivers = (ArrayList<User>) intent.getSerializableExtra("drivers");
        userId = (String) intent.getStringExtra("user_id");
        startPoint = (String) intent.getStringExtra("startPoint");
        endPoint = (String) intent.getStringExtra("endPoint");

        startPointField.setText(startPoint);
        endPointField.setText(endPoint);

        ListDriverAdapter driverAdapter = new ListDriverAdapter();
    }

    private void createBookingWithRandomDriver(User driver) {
        BookingControllers bookingControllers = new BookingControllers();
        // Assuming 'userId', 'startPoint', 'endPoint', 'distance', and 'price' are already defined
        bookingControllers.createNewBooking(userId, driver.getId(), "Pending", distance, price, startPoint, endPoint);
        Toast.makeText(this, "Booking created with driver: " + driver.getName(), Toast.LENGTH_SHORT).show();
        // Navigate to TripWaitingConfirmationActivity
        Intent intent = new Intent(BookingActivity.this, TripWaitingConfirmationActivity.class);
        intent.putExtra("driverId", driver.getId()); // Pass the driver ID to the next activity
        // Add any other booking details you need to pass to the next activity
        startActivity(intent);
    }
}