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
import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.ArrayList;
import java.util.List;

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

        RecyclerView recyclerView = findViewById(R.id.listAvailableDrivers);
        ListDriverAdapter driverAdapter = new ListDriverAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(BookingActivity.this, RecyclerView.VERTICAL, false));

        driverAdapter.setData(BookingActivity.this, userId, drivers, R.layout.driver_available, "Appending...", distance, price, startPoint, endPoint);
        recyclerView.setAdapter(driverAdapter);
    }
}